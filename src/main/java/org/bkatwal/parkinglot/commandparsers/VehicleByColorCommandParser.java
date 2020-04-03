package org.bkatwal.parkinglot.commandparsers;

import org.bkatwal.parkinglot.api.CommandParser;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;

public class VehicleByColorCommandParser implements CommandParser<String> {

  @Override
  public String parse(String[] params) {
    if (params == null || params.length != 1) {
      throw new ParkinglotException("Invalid command, expected one parameter. <color>");
    }

    return params[0];
  }
}
