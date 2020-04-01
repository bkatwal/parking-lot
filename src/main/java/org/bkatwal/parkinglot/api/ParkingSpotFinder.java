package org.bkatwal.parkinglot.api;

public interface ParkingSpotFinder {

  int getEmptySlot();

  void returnSlot(int slotNum);

  void createSlotQueues(int size);

  void destroy();
}
