package org.dineshv.pomdsl.exceptions

public class InvalidOptionException extends Exception{
  public InvalidOptionException(String message='Invalid state of the element') {
    super(message)
  }
}
