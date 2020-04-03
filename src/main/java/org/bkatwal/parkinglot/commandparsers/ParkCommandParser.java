package org.bkatwal.parkinglot.commandparsers;

import org.bkatwal.parkinglot.api.CommandParser;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;
import org.bkatwal.parkinglot.models.Car;
import org.bkatwal.parkinglot.models.Vehicle;
import org.bkatwal.parkinglot.utils.CommandEnum;

public class ParkCommandParser implements CommandParser<Vehicle> {

  @Override
  public Vehicle parse(final String[] params) {
    if (params.length != CommandEnum.PARK.numberOfParams()) {
      throw new ParkinglotException("Invalid params.");
    }

    return new Car(params[0], params[1]);
  }
}
