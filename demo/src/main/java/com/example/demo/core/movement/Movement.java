package com.example.demo.core.movement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.core.account.Account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MOVEMENT")
public class Movement
{
  @Id
  @Column(name = "MOVEMENT_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long movementId;

  @Column
  private LocalDateTime timeStamp;

  @Column
  private BigDecimal debit;

  @Column
  private BigDecimal credit;

  @Column
  private BigDecimal balance;

  @ManyToOne
  @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
  private Account account;

  @Enumerated
  @Column(columnDefinition = "smallint")
  private MovementType movementType;

  public Long getMovementId() {
    return movementId;
  }

  public void setMovementId(Long movementId) {
    this.movementId = movementId;
  }

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

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public MovementType getMovementType() {
    return movementType;
  }

  public void setMovementType(MovementType movementType) {
    this.movementType = movementType;
  }

}
