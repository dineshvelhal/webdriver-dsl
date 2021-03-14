package org.dineshv.pomdsl.exceptions

class UnsupportedFeatureException extends Exception{
  public UnsupportedFeatureException(String message='The feature is currently not supported') {
    super(message)
  }
}
