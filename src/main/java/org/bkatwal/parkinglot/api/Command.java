package org.bkatwal.parkinglot.api;

public interface Command<T> {

  /**
   * Executes command and returns execution result
   *
   * @param command command string
   * @return execution result
   */
  T execute(final String command);

  /**
   * validates the input command string
   *
   * @param command command string
   */
  void validate(final String command);
}
