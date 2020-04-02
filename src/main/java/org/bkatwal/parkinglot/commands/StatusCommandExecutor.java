package org.bkatwal.parkinglot.commands;

import java.util.Collection;
import org.bkatwal.parkinglot.api.CommandExecutor;
import org.bkatwal.parkinglot.api.ParkingService;
import org.bkatwal.parkinglot.models.ParkingSpot;

public class StatusCommandExecutor implements CommandExecutor<Void, Collection<ParkingSpot>> {

  private ParkingService parkingService;

  public StatusCommandExecutor(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public Collection<ParkingSpot> execute(Void voidP) {
    return parkingService.status();
  }
}
