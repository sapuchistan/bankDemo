package com.example.demo.core.account.web;

import java.math.BigDecimal;

public class AccountDTO
{
  private BigDecimal balance;

  private Long accountCountryNumber;

  private Long accountCityNumber;

  private Long accountOfficeNumber;

  private Long accountNumber;

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Long getAccountCountryNumber() {
    return accountCountryNumber;
  }

  public void setAccountCountryNumber(Long accountCountryNumber) {
    this.accountCountryNumber = accountCountryNumber;
  }

  public Long getAccountCityNumber() {
    return accountCityNumber;
  }

  public void setAccountCityNumber(Long accountCityNumber) {
    this.accountCityNumber = accountCityNumber;
  }

  public Long getAccountOfficeNumber() {
    return accountOfficeNumber;
  }

  public void setAccountOfficeNumber(Long accountOfficeNumber) {
    this.accountOfficeNumber = accountOfficeNumber;
  }

  public Long getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(Long accountNumber) {
    this.accountNumber = accountNumber;
  }

}
