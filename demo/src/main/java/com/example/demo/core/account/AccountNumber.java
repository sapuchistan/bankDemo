package com.example.demo.core.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACCOUNT_NUMBER_SEQUENCE")
public class AccountNumber
{
  @Id
  @Column(name = "ACCOUNT_NUMBER_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long accountNumberId;

  public Long getAccountNumberId() {
    return accountNumberId;
  }

  public void setAccountNumberId(Long accountNumberId) {
    this.accountNumberId = accountNumberId;
  }

}
