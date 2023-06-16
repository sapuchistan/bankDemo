package com.example.demo.core.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer
{
  @Id
  @Column(name = "CUSTOMER_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long customerId;

  @Column
  private String firstName;

  @Column
  private String lastName;

  public Customer() {

  }

  public Customer(String firstName, String lastName) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Customer(Long customerId, String firstName, String lastName) {
    super();
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

}
