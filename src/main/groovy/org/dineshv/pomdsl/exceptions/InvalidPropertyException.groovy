package org.dineshv.pomdsl.exceptions

class InvalidPropertyException extends Exception{
  public InvalidPropertyException(String message='Invalid property') {
    super(message)
  }
}
