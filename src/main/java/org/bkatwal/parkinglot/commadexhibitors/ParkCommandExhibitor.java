package org.bkatwal.parkinglot.commadexhibitors;

import org.bkatwal.parkinglot.api.CommandExhibitor;
import org.bkatwal.parkinglot.models.ParkingSpot;

public class ParkCommandExhibitor implements CommandExhibitor<ParkingSpot> {

  @Override
  public String exhibit(ParkingSpot parkingSpot) {
    return "Allocated slot number: " + parkingSpot.getSpotNumber();
  }
}
