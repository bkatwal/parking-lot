package org.bkatwal.parkinglot.models;

import java.util.Objects;

/**
 * A multi level parking system that has one entry gate at ground floor
 */
public class ParkingSpot implements Comparable<ParkingSpot> {

  private Integer spotNumber;

  private Vehicle vehicle;

  public ParkingSpot(int spotNumber, Vehicle vehicle) {
    this.vehicle = vehicle;
    this.spotNumber = spotNumber;
  }

  public int getSpotNumber() {
    return spotNumber;
  }

  public void setSpotNumber(int spotNumber) {
    this.spotNumber = spotNumber;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
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
