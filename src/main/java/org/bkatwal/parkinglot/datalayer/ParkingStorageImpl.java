package org.bkatwal.parkinglot.datalayer;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;
import org.bkatwal.parkinglot.models.ParkingSpot;
import org.bkatwal.parkinglot.models.Vehicle;

/**
 * this Parking Storage provides one type implementation. Any other implementation with its own
 * parking strategy can be implemented and injected to parking service
 */
public class ParkingStorageImpl implements ParkingStorage {

  private ParkingSpot[] parkingSpots;

  private Set<ParkingSpot> parkingSortedSet;

  private ParkingSpotFinder parkingSpotFinder;

  private ReentrantReadWriteLock lock;

  private AtomicInteger spotsOccupiedCounter;

  public ParkingStorageImpl(ParkingSpotFinder parkingSpotFinder) {
    this.parkingSpotFinder = parkingSpotFinder;
    lock = new ReentrantReadWriteLock();
    parkingSortedSet = new TreeSet<>();
  }

  @Override
  public Integer createParkingSpace(Integer maxSpace) {

    lock.writeLock().lock();
    try {
      if (parkingSpots != null) {
        throw new ParkinglotException(
            "Parking lot is already created with ".concat(parkingSpots.length + "")
                .concat(" slots. Reopen the application to re-create parking slots."));
      }
      parkingSpots = new ParkingSpot[maxSpace];
      parkingSpotFinder.createSlotQueues(maxSpace);
      spotsOccupiedCounter = new AtomicInteger(0);
      parkingSortedSet.clear();
      return parkingSpots.length;
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override
  public ParkingSpot addToSpot(Vehicle vehicle) {

    lock.writeLock().lock();
    try {
      validateParkingLotCreated();

      if (parkingSpots.length == spotsOccupiedCounter.get()) {
        throw new ParkinglotException("Sorry, parking lot is full");
      }
      Integer spot = parkingSpotFinder.getEmptySlot();
      ParkingSpot parkingSpot = new ParkingSpot(spot, vehicle);
      parkingSpots[spot - 1] = parkingSpot;
      parkingSortedSet.add(parkingSpot);
      spotsOccupiedCounter.incrementAndGet();
      return parkingSpot;
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override
  public ParkingSpot addToSpot(Integer spot, Vehicle vehicle) {
    lock.writeLock().lock();
    try {
      validateParkingLotCreated();

      if (parkingSpots.length == spotsOccupiedCounter.get()) {
        throw new ParkinglotException("Sorry, parking lot is full");
      }

      ParkingSpot parkingSpot = new ParkingSpot(spot, vehicle);
      parkingSpots[spot - 1] = parkingSpot;
      parkingSortedSet.add(parkingSpot);
      spotsOccupiedCounter.incrementAndGet();
      return parkingSpot;
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override
  public ParkingSpot removeFromSpot(Integer spot) {
    lock.writeLock().lock();
    try {
      validateParkingLotCreated();
      if (parkingSpots[spot - 1] == null) {
        throw new ParkinglotException("Slot is already empty.");
      }
      ParkingSpot parkingSpot = parkingSpots[spot - 1];
      parkingSpots[spot - 1] = null;
      parkingSortedSet.remove(parkingSpot);
      spotsOccupiedCounter.decrementAndGet();
      parkingSpotFinder.returnSlot(spot);
      return parkingSpot;
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override
  public Collection<ParkingSpot> status() {
    lock.readLock().lock();
    try {
      validateParkingLotCreated();
      return new TreeSet<>(parkingSortedSet);
    } finally {
      lock.readLock().unlock();
    }
  }

  @Override
  public ParkingSpot getFromSpot(Integer spot) {
    lock.readLock().lock();
    try {
      return parkingSpots[spot - 1];
    } finally {
      lock.readLock().unlock();
    }
  }

  @Override
  public void shutdown() {
    lock.writeLock().lock();
    try {
      if (parkingSpots == null) {
        throw new ParkinglotException("Parking lot is not created.");
      }
      parkingSpots = null;
      parkingSpotFinder.destroy();
      spotsOccupiedCounter = null;
    } finally {
      lock.writeLock().unlock();
    }
  }

  private void validateParkingLotCreated() {
    if (parkingSpots == null) {
      throw new ParkinglotException(
          "Parking lot is not created. Use \"create_parking_lot 5\" to create parking lot with 5 slots.");
    }
  }
}
