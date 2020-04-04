package org.bkatwal.parkinglot.commandparsers;

import org.bkatwal.parkinglot.api.CommandParser;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;
import org.bkatwal.parkinglot.utils.CommandEnum;

public class SlotByRegCommandParser implements CommandParser<String> {

  @Override
  public String parse(String[] params) {
    if (params == null || params.length != CommandEnum.SLOT_NUMBER_FOR_REG.numberOfParams()) {
      throw new ParkinglotException("Invalid command");
    }

    return params[0];
  }
}
