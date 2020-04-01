package org.bkatwal.parkinglot.core;

public interface Services {

  /**
   * Returns the service bean of given type
   *
   * @param serviceName bean name
   * @param clazz       the data type of the returned bean
   * @param <T>         any Object
   * @return service class bean
   */
  <T> T getService(final String serviceName, Class<T> clazz);

  void addService(final String serviceName, Object service);
}
