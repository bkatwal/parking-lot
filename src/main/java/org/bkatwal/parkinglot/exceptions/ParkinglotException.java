package org.bkatwal.parkinglot.exceptions;

public class ParkinglotException extends RuntimeException {

  private static final long serialVersionUID = 5387842093026695370L;

  public ParkinglotException() {
    super();
  }

  public ParkinglotException(String message, Throwable cause) {
    super(message, cause);
  }

  public ParkinglotException(String message) {
    super(message);
  }

  public ParkinglotException(Throwable cause) {
    super(cause);
  }
}
