package org.bkatwal.parkinglot.utils;

public final class ParkingLotUtils {

  private ParkingLotUtils() {
    throw new UnsupportedOperationException("Can't instantiate utility class!!");
  }

  @SuppressWarnings("unchecked")
  public static <T> T uncheckedCast(Object obj) {

    if (obj == null) {
      return null;
    }
    return (T) obj;
  }
}
