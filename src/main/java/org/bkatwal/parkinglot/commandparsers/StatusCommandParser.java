package org.bkatwal.parkinglot.commandparsers;

import org.bkatwal.parkinglot.api.CommandParser;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;
import org.bkatwal.parkinglot.utils.CommandEnum;

public class StatusCommandParser implements CommandParser<Void> {

  @Override
  public Void parse(String[] params) {
    if (params == null || params.length != CommandEnum.STATUS.numberOfParams()) {
      throw new ParkinglotException("Invalid command. No parameter expected.");
    }
    return null;
  }
}
