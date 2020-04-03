package org.bkatwal.parkinglot.commandexecutors;

import org.bkatwal.parkinglot.api.CommandExecutor;
import org.bkatwal.parkinglot.api.ParkingService;

public class LeaveCommandExecutor implements CommandExecutor<Integer, Integer> {

  private ParkingService parkingService;

  public LeaveCommandExecutor(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public Integer execute(Integer spot) {
    return parkingService.leave(spot);
  }
}
