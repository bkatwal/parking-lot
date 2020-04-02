package org.bkatwal.parkinglot.api;

public interface CommandExhibitor<T> {

  String exhibit(T output);
}
