package com.example.demo.error;

public class AccountException
    extends RuntimeException
{
  /**
   * 
   */
  private static final long serialVersionUID = -7727665077231817990L;

  public AccountException() {
    super();
  }

  public AccountException(String message) {
    super(message);
  }

  public AccountException(String message, Throwable cause) {
    super(message, cause);
  }

}
