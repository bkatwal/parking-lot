package org.bkatwal.parkinglot.models;

import java.util.Objects;

/**
 * A multi level parking system that has one entry gate at ground floor
 */
public class ParkingSpot implements Comparable<ParkingSpot> {

  private final Integer spotNumber;

  private final Vehicle vehicle;

  public ParkingSpot(Integer spotNumber, Vehicle vehicle) {
    this.vehicle = vehicle;
    this.spotNumber = spotNumber;
  }

  public Integer getSpotNumber() {
    return spotNumber;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  @Override
  public int compareTo(ParkingSpot o) {
    return this.spotNumber.compareTo(o.spotNumber);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParkingSpot that = (ParkingSpot) o;
    return Objects.equals(spotNumber, that.spotNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(spotNumber);
  }
}
