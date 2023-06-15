package com.example.demo.error;

public class EntityNotFoundException
    extends RuntimeException
{

  /**
   * 
   */
  private static final long serialVersionUID = -2944338158829018132L;

  public EntityNotFoundException() {
    super();
  }

  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
