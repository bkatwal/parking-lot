package org.bkatwal.parkinglot.commandexecutors;

import org.bkatwal.parkinglot.api.CommandExecutor;
import org.bkatwal.parkinglot.api.ParkingService;

public class SlotByRegCommandExecutor implements CommandExecutor<String, Integer> {

  private ParkingService parkingService;

  public SlotByRegCommandExecutor(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public Integer execute(final String registration) {
    return parkingService.findSlotNumberByRegistrationNumber(registration);
  }
}
