package org.dineshv.pomdsl.exceptions

public class InvalidPropertyException extends Exception{
  public InvalidPropertyException(String message='Invalid property') {
    super(message)
  }
}
