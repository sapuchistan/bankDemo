package com.example.demo.core.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepo
    extends JpaRepository<Account, Long>
{

  @Query("SELECT acc FROM Account acc WHERE acc.customer.customerId=:customerId ")
  public Optional<Account> findByCustomerUsingId(@Param("customerId") Long customerId);

  @Query("SELECT acc FROM Account acc WHERE acc.accountNumber.accountNumberId=:accountNumber ")
  public Optional<Account> findByAccountNumber(@Param("accountNumber") Long accountNumber);

}
