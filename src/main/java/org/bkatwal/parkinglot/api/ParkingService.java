package org.bkatwal.parkinglot.api;

import java.util.Collection;
import org.bkatwal.parkinglot.models.ParkingSpot;
import org.bkatwal.parkinglot.models.Vehicle;

public interface ParkingService {

  Integer createParkingSpace(Integer maxSpace);

  ParkingSpot park(Vehicle vehicle);

  ParkingSpot park(Integer spot, Vehicle vehicle);

  Integer leave(Integer spot);

  ParkingSpot getFromSpot(Integer spot);

  Collection<ParkingSpot> status();

  Collection<String> findVehiclesByColor(String color);

  Collection<Integer> findSlotNumbersByColor(String color);

  Integer findSlotNumberByRegistrationNumber(String registrationNumber);

  void shutdown();
}
