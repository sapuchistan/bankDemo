package com.example.demo.error;

public class AccountBalanceException
    extends RuntimeException
{
  /**
   * 
   */
  private static final long serialVersionUID = -7727665077231817990L;

  public AccountBalanceException() {
    super();
  }

  public AccountBalanceException(String message) {
    super(message);
  }

  public AccountBalanceException(String message, Throwable cause) {
    super(message, cause);
  }

}
