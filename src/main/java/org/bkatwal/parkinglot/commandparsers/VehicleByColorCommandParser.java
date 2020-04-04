package org.bkatwal.parkinglot.commandparsers;

import org.bkatwal.parkinglot.api.CommandParser;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;
import org.bkatwal.parkinglot.utils.CommandEnum;

public class VehicleByColorCommandParser implements CommandParser<String> {

  @Override
  public String parse(String[] params) {
    if (params == null || params.length != CommandEnum.REGISTRATION_NUMBERS_TO_COLOUR
        .numberOfParams()) {
      throw new ParkinglotException("Invalid command, expected one parameter. <color>");
    }

    return params[0];
  }
}
