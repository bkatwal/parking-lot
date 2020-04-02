package org.bkatwal.parkinglot.core;

import static org.bkatwal.parkinglot.utils.ServiceNameConstants.CACHE_SERVICE;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.bkatwal.parkinglot.api.ParkingService;
import org.bkatwal.parkinglot.cache.CacheService;
import org.bkatwal.parkinglot.cache.CacheServiceImpl;
import org.bkatwal.parkinglot.dao.ClosestEntrySpotFinder;
import org.bkatwal.parkinglot.dao.ParkingStorage;
import org.bkatwal.parkinglot.dao.ParkingStorageImpl;
import org.bkatwal.parkinglot.services.ParkingServiceImpl;

/**
 * Provides access to all the registered beans. Avoids any circular dependency and maintains one
 * object for each service
 */
public class ServiceLocator implements Services {

  private static Services serviceLocator;

  private Map<String, Object> serviceBeans;

  private ServiceLocator() {
    serviceBeans = new ConcurrentHashMap<>();
    registerBeans();
  }

  private void registerBeans() {
    ParkingStorage parkingStorage = new ParkingStorageImpl(new ClosestEntrySpotFinder());
    serviceBeans.put(parkingStorage.serviceName(), parkingStorage);

    ParkingService parkingService = new ParkingServiceImpl();
    serviceBeans.put(parkingService.serviceName(), parkingService);

    CacheService cacheService = new CacheServiceImpl();
    serviceBeans.put(CACHE_SERVICE, cacheService);
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
