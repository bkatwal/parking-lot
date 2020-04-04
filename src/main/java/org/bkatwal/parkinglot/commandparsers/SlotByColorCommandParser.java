package org.bkatwal.parkinglot.commandparsers;

import org.bkatwal.parkinglot.api.CommandParser;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;
import org.bkatwal.parkinglot.utils.CommandEnum;

public class SlotByColorCommandParser implements CommandParser<String> {

  @Override
  public String parse(String[] params) {
    if (params == null || params.length != CommandEnum.SLOT_NUMBERS_FOR_COLOR.numberOfParams()) {
      throw new ParkinglotException("Invalid command, expected one parameter. <color>");
    }

    return params[0];
  }
}
