package org.bkatwal.parkinglot.commands;

import org.bkatwal.parkinglot.api.CommandExecutor;
import org.bkatwal.parkinglot.api.ParkingService;

public class FindSlotByRegistrationExecutor implements CommandExecutor<String, Integer> {

  private ParkingService parkingService;

  public FindSlotByRegistrationExecutor(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public Integer execute(final String registration) {
    return parkingService.findSlotNumberByRegistrationNumber(registration);
  }
}
