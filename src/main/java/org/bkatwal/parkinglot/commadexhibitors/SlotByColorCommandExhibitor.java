package org.bkatwal.parkinglot.commadexhibitors;

import java.util.Collection;
import java.util.stream.Collectors;
import org.bkatwal.parkinglot.api.CommandExhibitor;

public class SlotByColorCommandExhibitor implements CommandExhibitor<Collection<Integer>> {

  @Override
  public String exhibit(Collection<Integer> output) {
    return output.stream().map(String::valueOf).collect(Collectors.joining(", "));
  }
}
