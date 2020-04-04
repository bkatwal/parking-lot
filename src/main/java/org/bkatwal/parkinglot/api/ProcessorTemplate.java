package org.bkatwal.parkinglot.api;

@FunctionalInterface
public interface ProcessorTemplate {

  String process(String command);
}
