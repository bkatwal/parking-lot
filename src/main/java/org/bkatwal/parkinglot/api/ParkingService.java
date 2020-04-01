package org.bkatwal.parkinglot.api;

import java.util.Collection;
import org.bkatwal.parkinglot.models.ParkingSpot;
import org.bkatwal.parkinglot.models.Vehicle;

public interface ParkingService {

  ParkingSpot park(Vehicle vehicle);

  Vehicle leave(ParkingSpot parkingSpot);

  Collection<ParkingSpot> status();

  Vehicle findVehicleByColor(String color);

  /**
   * Convenience method. this gives the service name of the bean, which will later be used to fetch
   * bean from service locator. Ideally this needs to be done using aspect
   *
   * @return service name
   */
  String serviceName();
}
