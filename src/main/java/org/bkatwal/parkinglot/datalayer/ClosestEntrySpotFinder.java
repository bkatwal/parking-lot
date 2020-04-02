package org.bkatwal.parkinglot.datalayer;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.IntStream;

public class ClosestEntrySpotFinder implements ParkingSpotFinder {

  private Queue<Integer> slotQueue;

  @Override
  public Integer getEmptySlot() {
    return slotQueue.poll();
  }

  @Override
  public void returnSlot(Integer slotNum) {
    slotQueue.offer(slotNum);
  }

  @Override
  public void createSlotQueues(Integer size) {
    slotQueue = new PriorityBlockingQueue<>(size);
    IntStream.rangeClosed(1, size).forEachOrdered(i -> slotQueue.add(i));
  }

  @Override
  public void destroy() {
    slotQueue.clear();
    slotQueue = null;
  }
}
