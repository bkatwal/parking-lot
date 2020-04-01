package org.bkatwal.parkinglot.services;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.IntStream;
import org.bkatwal.parkinglot.api.ParkingSpotFinder;

public class ClosestEntrySpotFinder implements ParkingSpotFinder {

  private Queue<Integer> slotQueue;

  @Override
  public int getEmptySlot() {
    return slotQueue.poll();
  }

  @Override
  public void returnSlot(int slotNum) {
    slotQueue.offer(slotNum);
  }

  @Override
  public void createSlotQueues(int size) {
    slotQueue = new PriorityBlockingQueue<>(size);
    IntStream.rangeClosed(1, size).forEachOrdered(i -> slotQueue.add(i));
  }

  @Override
  public void destroy() {
    slotQueue.clear();
    slotQueue = null;
  }
}
