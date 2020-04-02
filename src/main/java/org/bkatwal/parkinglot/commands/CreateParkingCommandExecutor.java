package org.bkatwal.parkinglot.commands;

import org.bkatwal.parkinglot.api.CommandExecutor;
import org.bkatwal.parkinglot.api.ParkingService;

public class CreateParkingCommandExecutor implements CommandExecutor<Integer, Integer> {

  private ParkingService parkingService;

  public CreateParkingCommandExecutor(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public Integer execute(final Integer maxSpace) {
    return parkingService.createParkingSpace(maxSpace);
  }
}
