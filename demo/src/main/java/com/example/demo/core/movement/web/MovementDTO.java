package com.example.demo.core.movement.web;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovementDTO
{

  private LocalDateTime timeStamp;

  private BigDecimal debit;

  private BigDecimal credit;

  private BigDecimal balance;

  private Long accountNumber;

  private String movementType;

  public LocalDateTime getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(LocalDateTime timeStamp) {
    this.timeStamp = timeStamp;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Long getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(Long accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getMovementType() {
    return movementType;
  }

  public void setMovementType(String movementType) {
    this.movementType = movementType;
  }

}
