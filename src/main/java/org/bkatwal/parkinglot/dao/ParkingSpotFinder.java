package org.bkatwal.parkinglot.dao;

public interface ParkingSpotFinder {

  int getEmptySlot();

  void returnSlot(int slotNum);

  void createSlotQueues(int size);

  void destroy();
}
