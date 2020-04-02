package org.bkatwal.parkinglot.services;

import static org.bkatwal.parkinglot.utils.CacheNameConstants.REGISTRATION_NUMBER_COLOR;
import static org.bkatwal.parkinglot.utils.CacheNameConstants.SLOT_NUMBERS_BY_COLOR;
import static org.bkatwal.parkinglot.utils.CacheNameConstants.SLOT_NUMBERS_BY_REGISTRATION;
import static org.bkatwal.parkinglot.utils.ServiceNameConstants.CACHE_SERVICE;
import static org.bkatwal.parkinglot.utils.ServiceNameConstants.PARKING_SERVICE;

import java.util.Collection;
import org.bkatwal.parkinglot.api.ParkingService;
import org.bkatwal.parkinglot.cache.CacheService;
import org.bkatwal.parkinglot.core.ServiceLocator;
import org.bkatwal.parkinglot.dao.ParkingStorage;
import org.bkatwal.parkinglot.models.ParkingSpot;
import org.bkatwal.parkinglot.models.Vehicle;

public class ParkingServiceImpl implements ParkingService {

  private CacheService cacheService;

  private ParkingStorage parkingStorage;

  public ParkingServiceImpl() {
    cacheService = ServiceLocator.getInstance().getService(CACHE_SERVICE, CacheService.class);
    parkingStorage = ServiceLocator.getInstance().getService(PARKING_SERVICE, ParkingStorage.class);

    cacheService.registerCache(REGISTRATION_NUMBER_COLOR);
    cacheService.registerCache(SLOT_NUMBERS_BY_COLOR);
    cacheService.registerCache(SLOT_NUMBERS_BY_REGISTRATION);
  }

  @Override
  public ParkingSpot park(Vehicle vehicle) {
    return null;
  }

  @Override
  public ParkingSpot park(int spot, Vehicle vehicle) {
    return null;
  }

  @Override
  public Vehicle leave(ParkingSpot parkingSpot) {
    return null;
  }

  @Override
  public Collection<ParkingSpot> status() {
    return null;
  }

  @Override
  public Vehicle findVehicleByColor(String color) {
    return null;
  }

  @Override
  public String serviceName() {
    return PARKING_SERVICE;
  }
}
