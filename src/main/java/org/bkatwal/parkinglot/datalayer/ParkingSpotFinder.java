package org.bkatwal.parkinglot.datalayer;

public interface ParkingSpotFinder {

  Integer getEmptySlot();

  void returnSlot(Integer slotNum);

  void createSlotQueues(Integer size);

  void destroy();
}
