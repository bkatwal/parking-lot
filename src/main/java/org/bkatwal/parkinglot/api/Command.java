package org.bkatwal.parkinglot.api;

@FunctionalInterface
public interface Command<U, V> {

  /**
   * Executes command and returns execution result
   *
   * @param param command params
   * @return execution result
   */
  V execute(final U param);
}
