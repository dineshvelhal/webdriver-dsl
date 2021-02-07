package org.dineshv.pomdsl.exceptions

class InvalidStateException extends Exception{
  public InvalidStateException(String message='Invalid state of the element') {
    super(message)
  }
}
