package org.bkatwal.parkinglot.commadexhibitors;

import org.bkatwal.parkinglot.api.CommandExhibitor;

public class LeaveCommandExhibitor implements CommandExhibitor<Integer> {

  @Override
  public String exhibit(final Integer slot) {
    return "Slot number " + slot + " is free";
  }
}
