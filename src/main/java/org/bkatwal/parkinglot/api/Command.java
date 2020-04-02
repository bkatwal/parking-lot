package org.bkatwal.parkinglot.api;

public interface Command<U, V> {

  /**
   * Executes command and returns execution result
   *
   * @param param command params
   * @return execution result
   */
  V execute(final U param);

  String serviceName();
}
