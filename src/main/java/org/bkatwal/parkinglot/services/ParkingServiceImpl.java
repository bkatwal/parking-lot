package org.bkatwal.parkinglot.services;

import static org.bkatwal.parkinglot.utils.CacheNameConstants.REGISTRATION_NUMBER_COLOR;
import static org.bkatwal.parkinglot.utils.CacheNameConstants.SLOT_NUMBERS_BY_COLOR;
import static org.bkatwal.parkinglot.utils.CacheNameConstants.SLOT_NUMBERS_BY_REGISTRATION;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.bkatwal.parkinglot.api.ParkingService;
import org.bkatwal.parkinglot.cache.CacheService;
import org.bkatwal.parkinglot.datalayer.ParkingStorage;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;
import org.bkatwal.parkinglot.models.ParkingSpot;
import org.bkatwal.parkinglot.models.Vehicle;

public class ParkingServiceImpl implements ParkingService {

  private CacheService cacheService;

  private ParkingStorage parkingStorage;

  public ParkingServiceImpl(CacheService cacheService, ParkingStorage parkingStorage) {
    this.cacheService = cacheService;
    this.parkingStorage = parkingStorage;
    registerCache();
  }

  private void registerCache() {
    cacheService.registerCache(REGISTRATION_NUMBER_COLOR);
    cacheService.registerCache(SLOT_NUMBERS_BY_COLOR);
    cacheService.registerCache(SLOT_NUMBERS_BY_REGISTRATION);
  }

  @Override
  public Integer createParkingSpace(final Integer maxSpace) {
    Integer slots = parkingStorage.createParkingSpace(maxSpace);
    registerCache();
    return slots;
  }

  @Override
  public ParkingSpot park(final Vehicle vehicle) {
    ParkingSpot parkingSpot = parkingStorage.addToSpot(vehicle);
    updateRegistrationCache(parkingSpot);
    updateSlotNumberByColorCache(parkingSpot);
    updateSlotNumberByRegCache(parkingSpot);
    return parkingSpot;
  }

  @Override
  public ParkingSpot park(final Integer spot, final Vehicle vehicle) {
    ParkingSpot parkingSpot = parkingStorage.addToSpot(spot, vehicle);
    updateRegistrationCache(parkingSpot);
    updateSlotNumberByColorCache(parkingSpot);
    updateSlotNumberByRegCache(parkingSpot);
    return parkingSpot;
  }

  @Override
  public Integer leave(final Integer spot) {
    ParkingSpot parkingSpot = parkingStorage.removeFromSpot(spot);
    removeRegCache(parkingSpot);
    removeSlotByColorCache(parkingSpot);
    removeSlotByRegCache(parkingSpot);
    return parkingSpot.getSpotNumber();
  }

  @Override
  public ParkingSpot getFromSpot(Integer spot) {
    return parkingStorage.getFromSpot(spot);
  }

  @Override
  public Collection<ParkingSpot> status() {
    return parkingStorage.status();
  }

  @Override
  public Collection<String> findVehiclesByColor(final String color) {
    if (color == null) {
      throw new ParkinglotException("Invalid color passed.");
    }
    List<String> vehicles = cacheService.getValue(REGISTRATION_NUMBER_COLOR, color);
    if (vehicles == null) {
      Collection<ParkingSpot> parkingInfo = parkingStorage.status();
      vehicles = parkingInfo.stream()
          .filter(parkingSpot -> color.equals(parkingSpot.getVehicle().getColor()))
          .map(parkingSpot -> parkingSpot.getVehicle().getRegistrationNumber())
          .collect(Collectors.toList());
      cacheService.put(REGISTRATION_NUMBER_COLOR, color, vehicles);
    }
    if (vehicles.isEmpty()) {
      throw new ParkinglotException("Not found");
    }
    return vehicles;
  }

  @Override
  public Collection<Integer> findSlotNumbersByColor(final String color) {
    if (color == null) {
      throw new ParkinglotException("Invalid color passed.");
    }
    List<Integer> slotNumbers = cacheService.getValue(SLOT_NUMBERS_BY_COLOR, color);
    if (slotNumbers == null) {
      Collection<ParkingSpot> parkingInfo = parkingStorage.status();
      slotNumbers = parkingInfo.stream()
          .filter(parkingSpot -> color.equals(parkingSpot.getVehicle().getColor()))
          .map(ParkingSpot::getSpotNumber)
          .collect(Collectors.toList());
      cacheService.put(SLOT_NUMBERS_BY_COLOR, color, slotNumbers);
    }
    if (slotNumbers.isEmpty()) {
      throw new ParkinglotException("Not found");
    }
    return slotNumbers;
  }

  @Override
  public Integer findSlotNumberByRegistrationNumber(final String registrationNumber) {
    Integer slotNumber = cacheService.getValue(SLOT_NUMBERS_BY_REGISTRATION, registrationNumber);

    if (slotNumber == null) {
      Collection<ParkingSpot> parkingInfo = parkingStorage.status();
      List<Integer> slotNumbers = parkingInfo.stream()
          .filter(parkingSpot -> registrationNumber
              .equals(parkingSpot.getVehicle().getRegistrationNumber()))
          .map(ParkingSpot::getSpotNumber)
          .collect(Collectors.toList());

      if (slotNumbers.isEmpty()) {
        throw new ParkinglotException("Not found");
      }
      slotNumber = slotNumbers.get(0);
    }

    return slotNumber;
  }

  @Override
  public void shutdown() {
    parkingStorage.shutdown();
    registerCache();
  }

  /**
   * updating cache. Ideally this needs to be taken care in an aspect
   *
   * @param parkingSpot parking spot used
   */
  private void updateRegistrationCache(final ParkingSpot parkingSpot) {
    //update registration cache
    List<String> registrationNumbers = cacheService
        .getValue(REGISTRATION_NUMBER_COLOR, parkingSpot.getVehicle().getColor());
    if (registrationNumbers == null) {
      registrationNumbers = new ArrayList<>();
    }
    registrationNumbers.add(parkingSpot.getVehicle().getRegistrationNumber());
    cacheService
        .put(REGISTRATION_NUMBER_COLOR, parkingSpot.getVehicle().getColor(), registrationNumbers);
  }

  private void updateSlotNumberByColorCache(final ParkingSpot parkingSpot) {
    //update slot numbers by color cache
    List<Integer> slotNumbersByColor = cacheService
        .getValue(SLOT_NUMBERS_BY_COLOR, parkingSpot.getVehicle().getColor());
    if (slotNumbersByColor == null) {
      slotNumbersByColor = new ArrayList<>();
    }
    slotNumbersByColor.add(parkingSpot.getSpotNumber());
    cacheService
        .put(SLOT_NUMBERS_BY_COLOR, parkingSpot.getVehicle().getColor(), slotNumbersByColor);
  }

  private void updateSlotNumberByRegCache(final ParkingSpot parkingSpot) {
    //update slot numbers by registration number cache
    cacheService
        .put(SLOT_NUMBERS_BY_REGISTRATION, parkingSpot.getVehicle().getRegistrationNumber(),
            parkingSpot.getSpotNumber());
  }

  private void removeRegCache(final ParkingSpot parkingSpot) {
    List<String> registrationNumbers = cacheService
        .getValue(REGISTRATION_NUMBER_COLOR, parkingSpot.getVehicle().getColor());
    if (registrationNumbers == null) {
      return;
    }
    registrationNumbers.remove(parkingSpot.getVehicle().getRegistrationNumber());
    cacheService
        .put(REGISTRATION_NUMBER_COLOR, parkingSpot.getVehicle().getColor(), registrationNumbers);
  }

  private void removeSlotByColorCache(final ParkingSpot parkingSpot) {

    List<Integer> slotNumbers = cacheService
        .getValue(SLOT_NUMBERS_BY_COLOR, parkingSpot.getVehicle().getColor());
    if (slotNumbers == null) {
      return;
    }
    slotNumbers.remove(parkingSpot.getSpotNumber());
    cacheService
        .put(SLOT_NUMBERS_BY_COLOR, parkingSpot.getVehicle().getColor(), slotNumbers);
  }

  private void removeSlotByRegCache(final ParkingSpot parkingSpot) {
    cacheService
        .remove(SLOT_NUMBERS_BY_REGISTRATION, parkingSpot.getVehicle().getRegistrationNumber());
  }
}
