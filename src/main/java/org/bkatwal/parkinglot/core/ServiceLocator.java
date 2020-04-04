package org.bkatwal.parkinglot.core;

import static org.bkatwal.parkinglot.utils.ServiceNameConstants.CACHE_SERVICE;
import static org.bkatwal.parkinglot.utils.ServiceNameConstants.COMMAND_PROCESSOR_TEMPLATE;
import static org.bkatwal.parkinglot.utils.ServiceNameConstants.CONSOLE_IO_HANDLER;
import static org.bkatwal.parkinglot.utils.ServiceNameConstants.FILE_IO_HANDLER;
import static org.bkatwal.parkinglot.utils.ServiceNameConstants.PARKING_SERVICE;
import static org.bkatwal.parkinglot.utils.ServiceNameConstants.PARKING_STORAGE;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.bkatwal.parkinglot.api.ParkingService;
import org.bkatwal.parkinglot.cache.CacheService;
import org.bkatwal.parkinglot.cache.CacheServiceImpl;
import org.bkatwal.parkinglot.commadexhibitors.CreateParkingExhibitor;
import org.bkatwal.parkinglot.commadexhibitors.LeaveCommandExhibitor;
import org.bkatwal.parkinglot.commadexhibitors.ParkCommandExhibitor;
import org.bkatwal.parkinglot.commadexhibitors.SlotByColorCommandExhibitor;
import org.bkatwal.parkinglot.commadexhibitors.SlotByRegCommandExhibitor;
import org.bkatwal.parkinglot.commadexhibitors.StatusCommandExhibitor;
import org.bkatwal.parkinglot.commadexhibitors.VehicleByColorCommandExhibitor;
import org.bkatwal.parkinglot.commandexecutors.CreateParkingCommandExecutor;
import org.bkatwal.parkinglot.commandexecutors.LeaveCommandExecutor;
import org.bkatwal.parkinglot.commandexecutors.ParkCommandExecutor;
import org.bkatwal.parkinglot.commandexecutors.SlotByColorCommandExecutor;
import org.bkatwal.parkinglot.commandexecutors.SlotByRegCommandExecutor;
import org.bkatwal.parkinglot.commandexecutors.StatusCommandExecutor;
import org.bkatwal.parkinglot.commandexecutors.VehicleByColorExecutor;
import org.bkatwal.parkinglot.commandparsers.CreateParkingCommandParser;
import org.bkatwal.parkinglot.commandparsers.LeaveCommandParser;
import org.bkatwal.parkinglot.commandparsers.ParkCommandParser;
import org.bkatwal.parkinglot.commandparsers.SlotByColorCommandParser;
import org.bkatwal.parkinglot.commandparsers.SlotByRegCommandParser;
import org.bkatwal.parkinglot.commandparsers.StatusCommandParser;
import org.bkatwal.parkinglot.commandparsers.VehicleByColorCommandParser;
import org.bkatwal.parkinglot.datalayer.ClosestEntrySpotFinder;
import org.bkatwal.parkinglot.datalayer.ParkingStorage;
import org.bkatwal.parkinglot.datalayer.ParkingStorageImpl;
import org.bkatwal.parkinglot.services.CommandProcessorTemplate;
import org.bkatwal.parkinglot.services.ConsoleIOHandler;
import org.bkatwal.parkinglot.services.FileIOHandler;
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


  public static synchronized Services getInstance() {
    if (serviceLocator == null) {
      serviceLocator = new ServiceLocator();
    }
    return serviceLocator;
  }

  /**
   * Creating beans using constructor injection, maintain order of beans as per dependency
   */
  private void registerBeans() {
    //register parking storage
    ParkingStorage parkingStorage = new ParkingStorageImpl(new ClosestEntrySpotFinder());
    serviceBeans.put(PARKING_STORAGE, parkingStorage);

    //register cache service
    CacheService cacheService = new CacheServiceImpl();
    serviceBeans.put(CACHE_SERVICE, cacheService);

    //register parking service
    ParkingService parkingService = new ParkingServiceImpl(cacheService, parkingStorage);
    serviceBeans.put(PARKING_SERVICE, parkingService);

    //register command executor
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
    serviceBeans
        .put(CommandEnum.STATUS.getExecutorName(), new StatusCommandExecutor(parkingService));

    //register command parsers
    serviceBeans.put(CommandEnum.PARK.getParserName(), new ParkCommandParser());
    serviceBeans
        .put(CommandEnum.CREATE_PARKING_LOT.getParserName(),
            new CreateParkingCommandParser());
    serviceBeans.put(CommandEnum.LEAVE.getParserName(), new LeaveCommandParser());
    serviceBeans.put(CommandEnum.REGISTRATION_NUMBERS_TO_COLOUR.getParserName(),
        new VehicleByColorCommandParser());
    serviceBeans
        .put(CommandEnum.SLOT_NUMBERS_FOR_COLOR.getParserName(),
            new SlotByColorCommandParser());
    serviceBeans
        .put(CommandEnum.SLOT_NUMBER_FOR_REG.getParserName(),
            new SlotByRegCommandParser());
    serviceBeans
        .put(CommandEnum.STATUS.getParserName(), new StatusCommandParser());

    //register command exhibitor
    serviceBeans.put(CommandEnum.PARK.getExhibitorName(), new ParkCommandExhibitor());
    serviceBeans
        .put(CommandEnum.CREATE_PARKING_LOT.getExhibitorName(),
            new CreateParkingExhibitor());
    serviceBeans.put(CommandEnum.LEAVE.getExhibitorName(), new LeaveCommandExhibitor());
    serviceBeans.put(CommandEnum.REGISTRATION_NUMBERS_TO_COLOUR.getExhibitorName(),
        new VehicleByColorCommandExhibitor());
    serviceBeans
        .put(CommandEnum.SLOT_NUMBERS_FOR_COLOR.getExhibitorName(),
            new SlotByColorCommandExhibitor());
    serviceBeans
        .put(CommandEnum.SLOT_NUMBER_FOR_REG.getExhibitorName(),
            new SlotByRegCommandExhibitor());
    serviceBeans
        .put(CommandEnum.STATUS.getExhibitorName(), new StatusCommandExhibitor());

    //register processor bean
    serviceBeans.put(COMMAND_PROCESSOR_TEMPLATE, new CommandProcessorTemplate());

    //register IO Handlers
    serviceBeans.put(CONSOLE_IO_HANDLER, new ConsoleIOHandler());
    serviceBeans.put(FILE_IO_HANDLER, new FileIOHandler());
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
