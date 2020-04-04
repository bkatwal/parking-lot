package org.bkatwal.parkinglot.services;

import static org.bkatwal.parkinglot.utils.ServiceNameConstants.COMMAND_PROCESSOR_TEMPLATE;

import java.util.Scanner;
import org.bkatwal.parkinglot.api.IOHandler;
import org.bkatwal.parkinglot.api.ProcessorTemplate;
import org.bkatwal.parkinglot.core.ServiceLocator;

public class ConsoleIOHandler implements IOHandler {

  @Override
  public void handle(String[] args) {

    ProcessorTemplate processorTemplate = ServiceLocator.getInstance()
        .getService(COMMAND_PROCESSOR_TEMPLATE,
            ProcessorTemplate.class);

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String line = scanner.nextLine();
      if (line.trim().equalsIgnoreCase("exit")) {
        break;
      }
      System.out.println(processorTemplate.process(line));
    }
  }
}
