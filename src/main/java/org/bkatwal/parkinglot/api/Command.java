package org.bkatwal.parkinglot.api;

import org.bkatwal.parkinglot.core.Services;

public abstract class Command<T> {

  protected Services services;
  /**
   * Executes command and returns execution result
   *
   * @param command command string
   * @return execution result
   */
  abstract T execute(final String command);

  /**
   * validates the input command string
   *
   * @param command command string
   */
  abstract void validate(final String command);

  /**
   * this gives the service name of the bean, which will later be used to fetch bean from service
   * locator. Ideally this needs to be done using aspect
   *
   * @return bean name
   */
  abstract String serviceName();
}
