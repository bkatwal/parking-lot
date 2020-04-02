package org.bkatwal.parkinglot.api;

public interface CommandParser<T> {

  /**
   * Parse and convert the command in service understandable format
   *
   * @param command command string
   * @return parsed param to command service
   */
  T parse(String command);
}
