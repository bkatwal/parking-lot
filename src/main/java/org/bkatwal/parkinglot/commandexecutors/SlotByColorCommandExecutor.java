package org.bkatwal.parkinglot.commandexecutors;

import java.util.Collection;
import org.bkatwal.parkinglot.api.CommandExecutor;
import org.bkatwal.parkinglot.api.ParkingService;

public class SlotByColorCommandExecutor implements CommandExecutor<String, Collection<Integer>> {

  private ParkingService parkingService;

  public SlotByColorCommandExecutor(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public Collection<Integer> execute(final String color) {
    return parkingService.findSlotNumbersByColor(color);
  }
}
