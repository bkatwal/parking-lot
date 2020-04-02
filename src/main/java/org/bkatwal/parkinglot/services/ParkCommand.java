package org.bkatwal.parkinglot.services;

import static org.bkatwal.parkinglot.utils.ServiceNameConstants.PARKING_SERVICE;

import org.bkatwal.parkinglot.api.Command;
import org.bkatwal.parkinglot.api.ParkingService;
import org.bkatwal.parkinglot.core.ServiceLocator;
import org.bkatwal.parkinglot.models.ParkingSpot;
import org.bkatwal.parkinglot.models.Vehicle;
import org.bkatwal.parkinglot.utils.CommandEnum;

public class ParkCommand implements Command<Vehicle, ParkingSpot> {

  private ParkingService parkingService;

  public ParkCommand() {
    parkingService = ServiceLocator.getInstance().getService(PARKING_SERVICE, ParkingService.class);
  }

  @Override
  public ParkingSpot execute(Vehicle vehicle) {
    return parkingService.park(vehicle);
  }

  @Override
  public String serviceName() {
    return CommandEnum.PARK.getName();
  }
}
