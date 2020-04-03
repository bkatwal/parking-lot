package org.bkatwal.parkinglot.commandexecutors;

import java.util.Collection;
import org.bkatwal.parkinglot.api.CommandExecutor;
import org.bkatwal.parkinglot.api.ParkingService;

public class VehicleByColorExecutor implements CommandExecutor<String, Collection<String>> {

  private ParkingService parkingService;

  public VehicleByColorExecutor(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public Collection<String> execute(final String color) {
    return parkingService.findVehiclesByColor(color);
  }
}
