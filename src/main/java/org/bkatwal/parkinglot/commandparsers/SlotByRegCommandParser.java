package org.bkatwal.parkinglot.commandparsers;

import org.bkatwal.parkinglot.api.CommandParser;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;

public class SlotByRegCommandParser implements CommandParser<String> {

  @Override
  public String parse(String[] params) {
    if (params == null || params.length != 1) {
      throw new ParkinglotException("Invalid command");
    }

    return params[0];
  }
}
