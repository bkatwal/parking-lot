package org.bkatwal.parkinglot.datalayer;

import static org.bkatwal.parkinglot.utils.ServiceNameConstants.PARKING_STORAGE;

import org.bkatwal.parkinglot.core.ServiceLocator;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;
import org.bkatwal.parkinglot.models.Car;
import org.bkatwal.parkinglot.models.ParkingSpot;
import org.bkatwal.parkinglot.models.Vehicle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingStorageTest {

  private ParkingStorage parkingStorage;

  @Before
  public void init() {
    parkingStorage = ServiceLocator.getInstance().getService(PARKING_STORAGE, ParkingStorage.class);
  }

  @After
  public void destroy() {
    parkingStorage.shutdown();
  }

  @Test
  public void assertParkingSpotCreation() {
    Integer totalSpace = parkingStorage.createParkingSpace(10);
    Assert.assertEquals(10, totalSpace.intValue());
  }

  @Test(expected = ParkinglotException.class)
  public void assertParkingSpotCreationFailure() {
    parkingStorage.createParkingSpace(10);
    parkingStorage.createParkingSpace(1);
  }

  @Test
  public void assertAddToSpot() {
    parkingStorage.createParkingSpace(3);
    Vehicle car = new Car("REG01", "RED");
    ParkingSpot parkingSpot1 = parkingStorage.addToSpot(car);
    Assert.assertNotNull(parkingSpot1);
    Assert.assertEquals(1, parkingSpot1.getSpotNumber().intValue());
    Assert.assertEquals("REG01", parkingSpot1.getVehicle().getRegistrationNumber());
    Assert.assertEquals("RED", parkingSpot1.getVehicle().getColor());

    Vehicle car2 = new Car("REG02", "BLUE");
    ParkingSpot parkingSpot2 = parkingStorage.addToSpot(car2);

    Vehicle car3 = new Car("REG03", "BLUE");
    ParkingSpot parkingSpot3 = parkingStorage.addToSpot(car3);
    Assert.assertEquals(3, parkingSpot3.getSpotNumber().intValue());

    parkingStorage.removeFromSpot(2);

    Vehicle car4 = new Car("REG04", "WHITE");
    ParkingSpot parkingSpot4 = parkingStorage.addToSpot(car4);
    Assert.assertEquals(2, parkingSpot4.getSpotNumber().intValue());
  }

  @Test(expected = ParkinglotException.class)
  public void assertFailureAddToSpot() {
    parkingStorage.createParkingSpace(2);
    Vehicle car2 = new Car("REG02", "BLUE");
    parkingStorage.addToSpot(car2);

    Vehicle car3 = new Car("REG03", "BLUE");
    parkingStorage.addToSpot(car3);

    Vehicle car = new Car("REG01", "RED");
    parkingStorage.addToSpot(car);
  }

  @Test(expected = ParkinglotException.class)
  public void assertRemoveFromAlreadyEmptySpot() {
    parkingStorage.createParkingSpace(2);
    parkingStorage.removeFromSpot(2);
  }

  @Test
  public void assertRemoveFromSpotAndAddToSpot() {

    parkingStorage.createParkingSpace(3);
    Vehicle car2 = new Car("REG02", "BLUE");
    parkingStorage.addToSpot(car2);

    Vehicle car3 = new Car("REG03", "BLUE");
    parkingStorage.addToSpot(car3);

    Vehicle car = new Car("REG01", "RED");
    parkingStorage.addToSpot(car);

    ParkingSpot parkingSpot = parkingStorage.removeFromSpot(2);
    Assert.assertEquals("REG03", parkingSpot.getVehicle().getRegistrationNumber());
    Assert.assertNull(parkingStorage.getFromSpot(2));

    parkingStorage.addToSpot(new Car("REG05", "GREEN"));
    Assert.assertNotNull(parkingStorage.getFromSpot(2));
  }

  @Test
  public void assertStatus() {
    parkingStorage.createParkingSpace(3);
    Vehicle car2 = new Car("REG02", "BLUE");
    parkingStorage.addToSpot(car2);

    Assert
        .assertEquals("REG02",
            parkingStorage.status().iterator().next().getVehicle().getRegistrationNumber());
    Assert
        .assertEquals("REG02",
            parkingStorage.status().iterator().next().getVehicle().getRegistrationNumber());

  }
}
