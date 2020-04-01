package org.bkatwal.parkinglot.models;

/**
 * A multi level parking system that has one entry gate at ground floor
 */
public class ParkingSpot implements Comparable<ParkingSpot> {

  private int spotNumber;

  private int level;

  @Override
  public int compareTo(ParkingSpot o) {
    return 0;
  }
}
