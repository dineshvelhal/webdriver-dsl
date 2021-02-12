package org.dineshv.pomdsl.exceptions

class InvalidKeyException extends Exception{
  public InvalidKeyException(String message='Invalid state of the element') {
    super(message)
  }
}
