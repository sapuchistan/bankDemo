package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.core.account.Account;
import com.example.demo.core.account.AccountNumber;
import com.example.demo.core.account.AccountRepo;
import com.example.demo.core.customer.Customer;
import com.example.demo.core.movement.MovementRepo;

@ExtendWith(SpringExtension.class)
class AtmServiceTest
{

  @InjectMocks
  private AtmService atmService;

  @Mock
  private AccountRepo accountRepo;

  @Mock
  private MovementRepo movementRepo;

  @Test
  public void testDeposit() {
    Customer customer1 = new Customer(1L, "Daffy", "Duck");
    AccountNumber accountNumber = new AccountNumber(1L);
    Account account1 = new Account(1L, customer1, 51L, 1L, 101L, accountNumber, new BigDecimal("0.0"));
    BigDecimal value = atmService.deposit(account1, new BigDecimal("100.50"), false);
    assertThat(value).isEqualTo(new BigDecimal("100.50"));
  }

  @Test
  public void testWithdraw() {
    Customer customer1 = new Customer(1L, "Daffy", "Duck");
    AccountNumber accountNumber = new AccountNumber(1L);
    Account account1 = new Account(1L, customer1, 51L, 1L, 101L, accountNumber, new BigDecimal("100.50"));
    BigDecimal value = atmService.withDraw(account1, new BigDecimal("100.50"), false);
    assertThat(value).isEqualTo(new BigDecimal("0.00"));
  }

  @Test
  public void testTransfer() {
    Customer customer1 = new Customer(1L, "Daffy", "Duck");
    Customer customer2 = new Customer(1L, "Elmer", "Fudd");
    AccountNumber accountNumber1 = new AccountNumber(1L);
    AccountNumber accountNumber2 = new AccountNumber(2L);
    Account account1 = new Account(1L, customer1, 51L, 1L, 101L, accountNumber1, new BigDecimal("100.50"));
    Account account2 = new Account(2L, customer2, 51L, 1L, 101L, accountNumber2, new BigDecimal("0.00"));
    BigDecimal value = atmService.transfer(account1, account2, new BigDecimal("100.50"));
    assertThat(value).isEqualTo(new BigDecimal("0.00"));
  }

  @Test
  public void testwithdraw_NotEnoughFund() {
    Customer customer1 = new Customer(1L, "Daffy", "Duck");
    AccountNumber accountNumber = new AccountNumber(1L);
    Account account1 = new Account(1L, customer1, 51L, 1L, 101L, accountNumber, new BigDecimal("100.50"));
    ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
      atmService.withDraw(account1, new BigDecimal("200.50"), false);
    });
    assertTrue(exception.getStatusCode().isSameCodeAs(HttpStatus.NOT_MODIFIED));
  }

  @Test
  void testwithdraw_NegativeValues() {
    Customer customer1 = new Customer(1L, "Daffy", "Duck");
    AccountNumber accountNumber = new AccountNumber(1L);
    Account account1 = new Account(1L, customer1, 51L, 1L, 101L, accountNumber, new BigDecimal("100.50"));
    ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
      atmService.withDraw(account1, new BigDecimal("-200.50"), false);
    });
    assertTrue(exception.getStatusCode().isSameCodeAs(HttpStatus.NOT_MODIFIED));
  }

  @Test
  public void testDeposit_NegativeValues() {
    Customer customer1 = new Customer(1L, "Daffy", "Duck");
    AccountNumber accountNumber = new AccountNumber(1L);
    Account account1 = new Account(1L, customer1, 51L, 1L, 101L, accountNumber, new BigDecimal("0.0"));
    ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
      atmService.deposit(account1, new BigDecimal("-100.50"), false);
    });
    assertTrue(exception.getStatusCode().isSameCodeAs(HttpStatus.NOT_MODIFIED));
  }

  @Test
  public void testTransfer_NegativeValues() {
    Customer customer1 = new Customer(1L, "Daffy", "Duck");
    Customer customer2 = new Customer(1L, "Elmer", "Fudd");
    AccountNumber accountNumber1 = new AccountNumber(1L);
    AccountNumber accountNumber2 = new AccountNumber(2L);
    Account account1 = new Account(1L, customer1, 51L, 1L, 101L, accountNumber1, new BigDecimal("100.50"));
    Account account2 = new Account(2L, customer2, 51L, 1L, 101L, accountNumber2, new BigDecimal("0.00"));
    ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
      atmService.transfer(account1, account2, new BigDecimal("-100.50"));
    });
    assertTrue(exception.getStatusCode().isSameCodeAs(HttpStatus.NOT_MODIFIED));
  }

  @Test
  public void testTransfer_NotEnoughFund() {
    Customer customer1 = new Customer(1L, "Daffy", "Duck");
    Customer customer2 = new Customer(1L, "Elmer", "Fudd");
    AccountNumber accountNumber1 = new AccountNumber(1L);
    AccountNumber accountNumber2 = new AccountNumber(2L);
    Account account1 = new Account(1L, customer1, 51L, 1L, 101L, accountNumber1, new BigDecimal("100.50"));
    Account account2 = new Account(2L, customer2, 51L, 1L, 101L, accountNumber2, new BigDecimal("0.00"));
    ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
      atmService.transfer(account1, account2, new BigDecimal("200.50"));
    });
    assertTrue(exception.getStatusCode().isSameCodeAs(HttpStatus.NOT_MODIFIED));
  }

  @Test
  public void testTransfer_SameAccount() {
    Customer customer1 = new Customer(1L, "Daffy", "Duck");
    AccountNumber accountNumber1 = new AccountNumber(1L);
    Account account1 = new Account(1L, customer1, 51L, 1L, 101L, accountNumber1, new BigDecimal("100.50"));
    ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
      atmService.transfer(account1, account1, new BigDecimal("100.50"));
    });
    assertTrue(exception.getStatusCode().isSameCodeAs(HttpStatus.NOT_MODIFIED));
  }
}
