package org.bkatwal.parkinglot.commandparsers;

import org.bkatwal.parkinglot.api.CommandParser;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;
import org.bkatwal.parkinglot.utils.CommandEnum;

public class LeaveCommandParser implements CommandParser<Integer> {

  @Override
  public Integer parse(String[] params) {
    if (params == null || params.length != CommandEnum.LEAVE.numberOfParams()) {
      throw new ParkinglotException("Invalid command, expected slot number.");
    }
    try {
      return Integer.parseInt(params[0]);
    } catch (NumberFormatException e) {
      throw new ParkinglotException("Invalid slot number passed");
    }
  }
}
