package org.bkatwal.parkinglot.core;

import static org.bkatwal.parkinglot.utils.ServiceNameConstants.CACHE_SERVICE;
import static org.bkatwal.parkinglot.utils.ServiceNameConstants.PARKING_SERVICE;
import static org.bkatwal.parkinglot.utils.ServiceNameConstants.PARKING_STORAGE;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.bkatwal.parkinglot.api.ParkingService;
import org.bkatwal.parkinglot.cache.CacheService;
import org.bkatwal.parkinglot.cache.CacheServiceImpl;
import org.bkatwal.parkinglot.commandexecutors.CreateParkingCommandExecutor;
import org.bkatwal.parkinglot.commandexecutors.SlotByColorCommandExecutor;
import org.bkatwal.parkinglot.commandexecutors.SlotByRegCommandExecutor;
import org.bkatwal.parkinglot.commandexecutors.VehicleByColorExecutor;
import org.bkatwal.parkinglot.commandexecutors.LeaveCommandExecutor;
import org.bkatwal.parkinglot.commandexecutors.ParkCommandExecutor;
import org.bkatwal.parkinglot.datalayer.ClosestEntrySpotFinder;
import org.bkatwal.parkinglot.datalayer.ParkingStorage;
import org.bkatwal.parkinglot.datalayer.ParkingStorageImpl;
import org.bkatwal.parkinglot.services.ParkingServiceImpl;
import org.bkatwal.parkinglot.utils.CommandEnum;

/**
 * Sort of beans container Provides access to all the registered beans. Avoids any circular
 * dependency and maintains one object for each service
 */
public class ServiceLocator implements Services {

  private static Services serviceLocator;

  private Map<String, Object> serviceBeans;

  private ServiceLocator() {
    serviceBeans = new ConcurrentHashMap<>();
    registerBeans();
  }

  /**
   * Creating beans using constructor injection, maintain order of beans as per dependency
   */
  private void registerBeans() {
    ParkingStorage parkingStorage = new ParkingStorageImpl(new ClosestEntrySpotFinder());
    serviceBeans.put(PARKING_STORAGE, parkingStorage);

    CacheService cacheService = new CacheServiceImpl();
    serviceBeans.put(CACHE_SERVICE, cacheService);

    ParkingService parkingService = new ParkingServiceImpl(cacheService, parkingStorage);
    serviceBeans.put(PARKING_SERVICE, parkingService);

    serviceBeans.put(CommandEnum.PARK.getExecutorName(), new ParkCommandExecutor(parkingService));
    serviceBeans
        .put(CommandEnum.CREATE_PARKING_LOT.getExecutorName(),
            new CreateParkingCommandExecutor(parkingService));
    serviceBeans.put(CommandEnum.LEAVE.getExecutorName(), new LeaveCommandExecutor(parkingService));
    serviceBeans.put(CommandEnum.REGISTRATION_NUMBERS_TO_COLOUR.getExecutorName(),
        new VehicleByColorExecutor(parkingService));
    serviceBeans
        .put(CommandEnum.SLOT_NUMBERS_FOR_COLOR.getExecutorName(),
            new SlotByColorCommandExecutor(parkingService));
    serviceBeans
        .put(CommandEnum.SLOT_NUMBER_FOR_REG.getExecutorName(),
            new SlotByRegCommandExecutor(parkingService));
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
