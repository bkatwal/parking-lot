package org.bkatwal.parkinglot.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.bkatwal.parkinglot.api.ParkingStorage;
import org.bkatwal.parkinglot.services.ClosestEntrySpotFinder;
import org.bkatwal.parkinglot.services.ParkingStorageImpl;

/**
 * Provides access to all the registered beans. Avoids any circular dependency and maintains one
 * object for each service
 */
public class ServiceLocator implements Services {

  private static Services serviceLocator;

  private Map<String, Object> serviceBeans;

  private ServiceLocator() {
    serviceBeans = new ConcurrentHashMap<>();

    ParkingStorage parkingStorage = new ParkingStorageImpl(new ClosestEntrySpotFinder());

    serviceBeans.put(parkingStorage.serviceName(), parkingStorage);
  }

  public static synchronized Services getInstance() {
    if (serviceLocator == null) {
      serviceLocator = new ServiceLocator();
    }
    return serviceLocator;
  }

  @Override
  public <T> T getService(String serviceName, Class<T> clazz) {
    return clazz.cast(serviceBeans.get(serviceName));
  }

  @Override
  public void addService(String serviceName, Object service) {
    serviceBeans.put(serviceName, service);
  }
}
