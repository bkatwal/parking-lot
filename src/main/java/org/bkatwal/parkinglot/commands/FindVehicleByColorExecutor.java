package org.bkatwal.parkinglot.commands;

import java.util.Collection;
import org.bkatwal.parkinglot.api.CommandExecutor;
import org.bkatwal.parkinglot.api.ParkingService;

public class FindVehicleByColorExecutor implements CommandExecutor<String, Collection<String>> {

  private ParkingService parkingService;

  public FindVehicleByColorExecutor(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public Collection<String> execute(final String color) {
    return parkingService.findVehiclesByColor(color);
  }
}
