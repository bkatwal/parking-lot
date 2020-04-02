package org.bkatwal.parkinglot.services;

import static org.bkatwal.parkinglot.utils.CacheNameConstants.REGISTRATION_NUMBER_COLOR;
import static org.bkatwal.parkinglot.utils.CacheNameConstants.SLOT_NUMBERS_BY_COLOR;
import static org.bkatwal.parkinglot.utils.ServiceNameConstants.CACHE_SERVICE;
import static org.bkatwal.parkinglot.utils.ServiceNameConstants.PARKING_SERVICE;

import java.util.Collection;
import java.util.List;
import org.bkatwal.parkinglot.api.ParkingService;
import org.bkatwal.parkinglot.cache.CacheService;
import org.bkatwal.parkinglot.core.ServiceLocator;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;
import org.bkatwal.parkinglot.models.Car;
import org.bkatwal.parkinglot.models.ParkingSpot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingServiceTest {

  private ParkingService parkingService;
  private CacheService cacheService;

  @Before
  public void init() {
    cacheService = ServiceLocator.getInstance().getService(CACHE_SERVICE, CacheService.class);
    parkingService = ServiceLocator.getInstance().getService(PARKING_SERVICE, ParkingService.class);
  }

  @After
  public void destroy() {
    try {
      parkingService.shutdown();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void assertCreateParkingLot() {
    Integer slots = parkingService.createParkingSpace(10);
    Assert.assertEquals(10, slots.intValue());
  }

  @Test(expected = ParkinglotException.class)
  public void assertShutdown() {
    parkingService.createParkingSpace(10);
    parkingService.shutdown();
    parkingService.status();
  }

  @Test
  public void assertPark() {
    parkingService.createParkingSpace(3);
    parkingService.park(new Car("reg1", "red"));
    Collection<ParkingSpot> parkingSpots = parkingService.status();
    Assert
        .assertTrue(
            "reg1".equalsIgnoreCase(
                parkingSpots.iterator().next().getVehicle().getRegistrationNumber()));
  }

  @Test
  public void assertLeave() {
    parkingService.createParkingSpace(3);
    parkingService.park(new Car("reg1", "red"));
    parkingService.park(new Car("reg2", "blue"));
    parkingService.leave(1);
    Assert.assertNull(parkingService.getFromSpot(1));
    parkingService.park(new Car("reg3", "green"));
    Assert.assertNotNull(parkingService.getFromSpot(1));
  }

  @Test
  public void assertFindVehiclesByColor() {
    parkingService.createParkingSpace(3);
    parkingService.park(new Car("reg1", "red"));
    parkingService.park(new Car("reg2", "red"));
    parkingService.park(new Car("reg3", "blue"));
    List<String> regNumbers = (List<String>) parkingService.findVehiclesByColor("red");
    Assert.assertEquals(2, regNumbers.size());
    Assert.assertTrue(regNumbers.contains("reg1"));
    Assert.assertTrue(regNumbers.contains("reg2"));

    cacheService.invalidateCache(REGISTRATION_NUMBER_COLOR);
    parkingService.leave(2);
    regNumbers = (List<String>) parkingService.findVehiclesByColor("red");
    Assert.assertEquals(1, regNumbers.size());
    Assert.assertTrue(regNumbers.contains("reg1"));
    Assert.assertFalse(regNumbers.contains("reg2"));
  }

  @Test
  public void assertFindSlotsByColor() {
    parkingService.createParkingSpace(3);
    parkingService.park(new Car("reg1", "blue"));
    parkingService.park(new Car("reg2", "red"));
    parkingService.park(new Car("reg3", "red"));
    List<Integer> slots = (List<Integer>) parkingService.findSlotNumbersByColor("red");
    Assert.assertEquals(2, slots.size());
    Assert.assertTrue(slots.contains(2));
    Assert.assertTrue(slots.contains(3));

    cacheService.invalidateCache(SLOT_NUMBERS_BY_COLOR);
    parkingService.leave(2);
    slots = (List<Integer>) parkingService.findSlotNumbersByColor("red");
    Assert.assertEquals(1, slots.size());
    Assert.assertTrue(slots.contains(3));
    Assert.assertFalse(slots.contains(2));
  }

  @Test
  public void assertFindSlotByRegistration() {
    parkingService.createParkingSpace(3);
    parkingService.park(new Car("reg1", "blue"));
    parkingService.park(new Car("reg2", "red"));
    parkingService.park(new Car("reg3", "red"));
    Integer slot = parkingService.findSlotNumberByRegistrationNumber("reg2");
    Assert.assertEquals(2, slot.intValue());
  }

  @Test
  public void assertFindSLotNumberByRegistrationEmptySlot() {

    parkingService.createParkingSpace(3);
    parkingService.park(new Car("reg1", "blue"));
    parkingService.park(new Car("reg2", "red"));
    parkingService.park(new Car("reg3", "red"));
    parkingService.leave(2);
    try {
      parkingService.findSlotNumberByRegistrationNumber("reg2");
    } catch (ParkinglotException e) {
      Assert.assertEquals("Not found", e.getMessage());
    }
  }
}
