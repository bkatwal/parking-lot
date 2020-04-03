package org.bkatwal.parkinglot.commadexhibitors;

import org.bkatwal.parkinglot.api.CommandExhibitor;

public class CreateParkingExhibitor implements CommandExhibitor<Integer> {

  @Override
  public String exhibit(final Integer output) {
    return "Created a parking lot with " + output + " slots";
  }
}
