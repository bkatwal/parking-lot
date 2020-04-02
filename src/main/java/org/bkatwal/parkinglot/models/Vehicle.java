package org.bkatwal.parkinglot.models;

import java.util.Objects;

/**
 * any vehicle that the parking lot supports need to inherit this class
 */
public abstract class Vehicle {

  protected final String registrationNumber;
  protected final String color;

  public Vehicle(String registrationNumber, String color) {
    this.registrationNumber = registrationNumber;
    this.color = color;
  }


  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public String getColor() {
    return color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vehicle vehicle = (Vehicle) o;
    return registrationNumber.equals(vehicle.registrationNumber) &&
        color.equals(vehicle.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(registrationNumber, color);
  }
}
