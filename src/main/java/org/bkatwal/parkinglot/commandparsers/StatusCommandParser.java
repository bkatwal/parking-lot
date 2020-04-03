package org.bkatwal.parkinglot.commandparsers;

import org.bkatwal.parkinglot.api.CommandParser;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;

public class StatusCommandParser implements CommandParser<Void> {

  @Override
  public Void parse(String[] params) {
    if (params == null || params.length != 0) {
      throw new ParkinglotException("Invalid command. No parameter expected.");
    }
    return null;
  }
}
