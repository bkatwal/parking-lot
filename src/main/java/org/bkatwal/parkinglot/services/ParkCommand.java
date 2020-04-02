package org.bkatwal.parkinglot.services;

import org.bkatwal.parkinglot.api.Command;
import org.bkatwal.parkinglot.api.ParkingService;
import org.bkatwal.parkinglot.models.ParkingSpot;
import org.bkatwal.parkinglot.models.Vehicle;

public class ParkCommand implements Command<Vehicle, ParkingSpot> {

  private ParkingService parkingService;

  public ParkCommand(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  @Override
  public ParkingSpot execute(Vehicle vehicle) {
    return parkingService.park(vehicle);
  }
}
