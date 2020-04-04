package org.bkatwal.parkinglot.commandparsers;

import org.bkatwal.parkinglot.api.CommandParser;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;
import org.bkatwal.parkinglot.utils.CommandEnum;

public class CreateParkingCommandParser implements CommandParser<Integer> {

  @Override
  public Integer parse(String[] params) {
    if (params == null || params.length != CommandEnum.CREATE_PARKING_LOT.numberOfParams()) {
      throw new ParkinglotException("Invalid command");
    }
    try {
      return Integer.parseInt(params[0]);
    } catch (NumberFormatException e) {
      throw new ParkinglotException("Invalid parking ot size passed");
    }
  }
}
