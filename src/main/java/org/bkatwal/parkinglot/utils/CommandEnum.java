package org.bkatwal.parkinglot.utils;

public enum CommandEnum {

  CAR_PARKING_LOT("create_parking_lot", 1),
  PARK("park", 2),
  LEAVE("leave", 1),
  REGISTRATION_NUMBERS_TO_COLOUR("registration_numbers_for_cars_with_colour", 1),
  SLOT_NUMBERS_FOR_COLOR("slot_numbers_for_cars_with_colour", 1),
  SOLT_NUMBER_FOR_REG("slot_number_for_registration_number", 1);

  private final String command;
  private final int noOfParams;

  CommandEnum(String command, int noOfParams) {
    this.command = command;
    this.noOfParams = noOfParams;
  }

  public String getName() {
    return this.command;
  }

  public int numberOfParams() {
    return this.noOfParams;
  }

  public static CommandEnum getCommandByName(String name) {

    if (name == null) {
      return null;
    }
    for (CommandEnum commandEnum : CommandEnum.values()) {
      if (commandEnum.command.equals(name)) {
        return commandEnum;
      }
    }
    return null;
  }
}
