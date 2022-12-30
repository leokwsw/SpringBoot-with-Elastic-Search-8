package dev.leonardaprk.springbootelasticsearch.exception;

public class DuplicateIsbnException extends Exception {

  public DuplicateIsbnException(String message) {
    super(message);
  }
}
