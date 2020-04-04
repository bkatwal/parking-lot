package org.bkatwal.parkinglot;

import static org.bkatwal.parkinglot.utils.ServiceNameConstants.CONSOLE_IO_HANDLER;
import static org.bkatwal.parkinglot.utils.ServiceNameConstants.FILE_IO_HANDLER;

import org.bkatwal.parkinglot.api.IOHandler;
import org.bkatwal.parkinglot.core.ServiceLocator;
import org.bkatwal.parkinglot.core.Services;

public class ParkingLotApp {

  public static void main(String[] args) {

    Services services = ServiceLocator.getInstance();
    if (args.length == 1) {
      services.getService(FILE_IO_HANDLER, IOHandler.class).handle(args);
      System.exit(0);
    }

    services.getService(CONSOLE_IO_HANDLER, IOHandler.class).handle(args);
  }
}
