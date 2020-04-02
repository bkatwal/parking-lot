package org.bkatwal.parkinglot.api;

@FunctionalInterface
public interface CommandParser<T> {

  /**
   * Parse and convert the command in service understandable format
   *
   * @param params command params
   * @return parsed param to command service
   */
  T parse(String[] params);
}
