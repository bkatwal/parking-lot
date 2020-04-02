package org.bkatwal.parkinglot.commands;

import java.util.Collection;
import org.bkatwal.parkinglot.api.CommandExecutor;
import org.bkatwal.parkinglot.api.ParkingService;

public class FindSlotByColorExecutor implements CommandExecutor<String, Collection<Integer>> {

  private ParkingService parkingService;

  public FindSlotByColorExecutor(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public Collection<Integer> execute(final String color) {
    return parkingService.findSlotNumbersByColor(color);
  }
}
