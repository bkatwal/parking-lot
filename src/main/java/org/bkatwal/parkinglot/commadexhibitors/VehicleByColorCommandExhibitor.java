package org.bkatwal.parkinglot.commadexhibitors;

import java.util.Collection;
import org.bkatwal.parkinglot.api.CommandExhibitor;

public class VehicleByColorCommandExhibitor implements CommandExhibitor<Collection<String>> {

  @Override
  public String exhibit(Collection<String> vehicles) {
    return String.join(", ", vehicles);
  }
}
