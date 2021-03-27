package org.dineshv.pomdsl.exceptions

public class UnknownDestinationException extends Exception{
  public UnknownDestinationException(String message='Unknown Destination') {
    super(message)
  }
}
