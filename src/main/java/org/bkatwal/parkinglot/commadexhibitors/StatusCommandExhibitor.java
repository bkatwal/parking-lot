package org.bkatwal.parkinglot.commadexhibitors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.bkatwal.parkinglot.api.CommandExhibitor;
import org.bkatwal.parkinglot.models.Car;
import org.bkatwal.parkinglot.models.ParkingSpot;

public class StatusCommandExhibitor implements CommandExhibitor<Collection<ParkingSpot>> {

  @Override
  public String exhibit(Collection<ParkingSpot> parkingSpots) {
    StringBuilder sb = new StringBuilder();
    String header = "Slot No.\t\tRegistration No.\t\tColour";
    sb.append(header);
    for (ParkingSpot parkingSpot : parkingSpots) {
      String spotNumber = String.valueOf(parkingSpot.getSpotNumber());
      int spaces1 = 8 - spotNumber.length();
      int spaces2 = 16 - parkingSpot.getVehicle().getRegistrationNumber().length();
      sb.append("\n");
      (
          sb.append(parkingSpot.getSpotNumber()).append("\t\t\t\t\t\t"))
          .append(parkingSpot.getVehicle().getRegistrationNumber()).append("\t\t\t\t")
          .append(parkingSpot.getVehicle().getColor());
    }
    return sb.toString();
  }

  private String getSpaces(int numSpaces) {
    if (numSpaces <= 0) {
      return "";
    }
    StringBuilder spaces = new StringBuilder();
    for (int i = 0; i < numSpaces; i++) {
      spaces.append(" ");
    }
    spaces.append("        ");
    return spaces.toString();
  }

  public static void main(String[] args) {
    List<ParkingSpot> parkingSpots = new ArrayList<>();
    parkingSpots.add(new ParkingSpot(1, new Car("KA-01-HH-1234", "red")));
    parkingSpots.add(new ParkingSpot(2, new Car("KA-01-HH-1234", "blue")));
    parkingSpots.add(new ParkingSpot(3, new Car("KA-01-HH-1234", "green")));
    StatusCommandExhibitor statusCommandExhibitor = new StatusCommandExhibitor();
    System.out.println(statusCommandExhibitor.exhibit(parkingSpots));
  }
}
