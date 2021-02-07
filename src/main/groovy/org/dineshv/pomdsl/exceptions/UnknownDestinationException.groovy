package org.dineshv.pomdsl.exceptions

class UnknownDestinationException extends Exception{
  public UnknownDestinationException(String message='Unknown Destination') {
    super(message)
  }
}
