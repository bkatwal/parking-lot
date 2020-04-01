package org.bkatwal.parkinglot.models;

import java.util.Objects;

/**
 * any vehicle that the parking lot supports need to inherit this class
 */
public abstract class Vehicle{

  protected String registrationNumber;
  protected String color;

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
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
