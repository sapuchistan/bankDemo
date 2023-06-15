package com.example.demo.service;

import java.math.BigDecimal;

import com.example.demo.core.account.Account;

public interface Atm
{
  public BigDecimal deposit(Account account, BigDecimal credit, Boolean isTransfer);

  public BigDecimal withDraw(Account account, BigDecimal debit, Boolean isTransfer);

  public BigDecimal transfer(Account origin, Account destiny, BigDecimal debit);

}
