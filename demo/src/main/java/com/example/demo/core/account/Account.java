package com.example.demo.core.account;

import java.math.BigDecimal;

import com.example.demo.core.customer.Customer;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ACCOUNT_ID")
  private Long accountId;

  @OneToOne
  @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
  @Nonnull
  private Customer customer;

  @Column(name = "ACCOUNT_COUNTRY")
  @Nonnull
  private Long accountCountryNumber;

  @Column(name = "ACCOUNT_CITY")
  @Nonnull
  private Long accountCityNumber;

  @Column(name = "ACCOUNT_OFFICE")

  @Nonnull
  private Long accountOfficeNumber;

  @OneToOne
  @JoinColumn(name = "ACCOUNT_NUMBER", referencedColumnName = "ACCOUNT_NUMBER_ID")
  @Nonnull
  private AccountNumber accountNumber;

  private BigDecimal balance;

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
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

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public AccountNumber getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(AccountNumber accountNumber) {
    this.accountNumber = accountNumber;
  }
}
