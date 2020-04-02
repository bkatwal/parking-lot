package org.bkatwal.parkinglot.commands;

import org.bkatwal.parkinglot.api.CommandExecutor;
import org.bkatwal.parkinglot.api.ParkingService;
import org.bkatwal.parkinglot.models.ParkingSpot;
import org.bkatwal.parkinglot.models.Vehicle;

public class ParkCommandExecutor implements CommandExecutor<Vehicle, ParkingSpot> {

  private ParkingService parkingService;

  public ParkCommandExecutor(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public ParkingSpot execute(final Vehicle vehicle) {
    return parkingService.park(vehicle);
  }
}
