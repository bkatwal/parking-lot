package org.bkatwal.parkinglot.services;

import static org.bkatwal.parkinglot.utils.ServiceNameConstants.COMMAND_PROCESSOR_TEMPLATE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.bkatwal.parkinglot.api.IOHandler;
import org.bkatwal.parkinglot.api.ProcessorTemplate;
import org.bkatwal.parkinglot.core.ServiceLocator;

public class FileIOHandler implements IOHandler {

  @Override
  public void handle(String[] args) {

    ProcessorTemplate processorTemplate = ServiceLocator.getInstance()
        .getService(COMMAND_PROCESSOR_TEMPLATE,
            ProcessorTemplate.class);

    try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
      stream.forEach(command -> System.out.println(processorTemplate.process(command)));
    } catch (IOException e) {
      System.out.println("Not a valid file.");
    }
  }
}
