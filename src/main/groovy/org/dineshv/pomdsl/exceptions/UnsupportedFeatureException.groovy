package org.dineshv.pomdsl.exceptions

public class UnsupportedFeatureException extends Exception{
  public UnsupportedFeatureException(String message='The feature is currently not supported') {
    super(message)
  }
}
