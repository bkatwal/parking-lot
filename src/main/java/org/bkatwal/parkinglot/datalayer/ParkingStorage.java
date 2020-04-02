package org.bkatwal.parkinglot.datalayer;

import java.util.Collection;
import org.bkatwal.parkinglot.models.ParkingSpot;
import org.bkatwal.parkinglot.models.Vehicle;

public interface ParkingStorage {

  Integer createParkingSpace(Integer totalSpace);

  ParkingSpot addToSpot(Vehicle vehicle);

  ParkingSpot addToSpot(Integer spot, Vehicle vehicle);

  ParkingSpot removeFromSpot(Integer spot);

  Collection<ParkingSpot> status();

  ParkingSpot getFromSpot(Integer spot);

  void shutdown();
}
