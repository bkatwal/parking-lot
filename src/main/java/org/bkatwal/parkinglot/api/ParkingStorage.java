package org.bkatwal.parkinglot.api;

import java.util.Iterator;
import org.bkatwal.parkinglot.models.ParkingSpot;
import org.bkatwal.parkinglot.models.Vehicle;

public interface ParkingStorage {

  int createParkingSpace(int levelSpace);

  ParkingSpot addToSpot(Vehicle vehicle);

  ParkingSpot removeFromSpot(int spot);

  Iterator<ParkingSpot> status();

  ParkingSpot getFromSpot(int spot);

  void shutdown();

  /**
   * Convenience method. this gives the service name of the bean, which will later be used to fetch
   * bean from service locator. Ideally this needs to be done using aspect
   *
   * @return service name
   */
  String serviceName();
}
