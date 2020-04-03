package org.bkatwal.parkinglot.commadexhibitors;

import org.bkatwal.parkinglot.api.CommandExhibitor;

public class SlotByRegCommandExhibitor implements CommandExhibitor<Integer> {

  @Override
  public String exhibit(final Integer slot) {
    return String.valueOf(slot);
  }
}
