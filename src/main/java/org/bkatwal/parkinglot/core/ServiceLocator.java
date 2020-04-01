package org.bkatwal.parkinglot.core;

public interface ServiceLocator {

  <T> T getService(String serviceName, T type);
}
