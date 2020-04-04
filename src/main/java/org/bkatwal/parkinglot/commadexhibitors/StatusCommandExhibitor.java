package org.bkatwal.parkinglot.commadexhibitors;

import java.util.Collection;
import org.bkatwal.parkinglot.api.CommandExhibitor;
import org.bkatwal.parkinglot.models.ParkingSpot;

public class StatusCommandExhibitor implements CommandExhibitor<Collection<ParkingSpot>> {

  @Override
  public String exhibit(Collection<ParkingSpot> parkingSpots) {
    StringBuilder sb = new StringBuilder();
    String header = "Slot No.\t\tRegistration No.\t\tColour";
    sb.append(header);
    for (ParkingSpot parkingSpot : parkingSpots) {
      sb.append("\n");
      (
          sb.append(parkingSpot.getSpotNumber()).append("\t\t\t"))
          .append(parkingSpot.getVehicle().getRegistrationNumber()).append("\t\t\t")
          .append(parkingSpot.getVehicle().getColor());
    }
    return sb.toString();
  }
}
