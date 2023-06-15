package com.example.demo.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.core.account.Account;
import com.example.demo.core.account.AccountService;
import com.example.demo.core.account.web.AccountDTO;
import com.example.demo.core.customer.CustomerService;
import com.example.demo.core.customer.web.CustomerDTO;
import com.example.demo.core.movement.MovementService;
import com.example.demo.core.movement.web.MovementDTO;
import com.example.demo.service.web.DepositRequest;
import com.example.demo.service.web.TransferRequest;
import com.example.demo.service.web.WithdrawRequest;

@Service
public class BankService
{
  private final CustomerService customerService;

  private final AccountService accountService;

  private final AtmService atmService;

  private final MovementService movementService;

  BankService(
      CustomerService customerService,
      AccountService accountService,
      AtmService atmService,
      MovementService movementService)
  {
    this.customerService = customerService;
    this.accountService = accountService;
    this.atmService = atmService;
    this.movementService = movementService;
  }

  public Page<CustomerDTO> findAllCustomers(Pageable pageable) {
    return customerService.findAllCustomers(pageable);
  }

  public AccountDTO getAccount(Long id) {
    return accountService.getAccount(id);
  }

  public AccountDTO getAccountByCustomer(Long idCustomer) {
    return accountService.getAccountByCustomer(idCustomer);
  }

  public BigDecimal depositFunds(DepositRequest depositRequest) {
    Account account = accountService.findByCustomerOrThrow(depositRequest.getIdCustomer());
    return atmService.deposit(account, depositRequest.getCredit(), false);
  }

  public BigDecimal withdrawFunds(WithdrawRequest withdrawRequest) {
    Account account = accountService.findByCustomerOrThrow(withdrawRequest.getIdCustomer());
    return atmService.withDraw(account, withdrawRequest.getDebit(), false);
  }

  public BigDecimal transferFunds(TransferRequest transferRequest) {
    Account accountOrigin = accountService.findByCustomerOrThrow(transferRequest.getIdCustomer());
    Account accountDestiny = accountService.findByAccountNumberOrThrow(transferRequest.getAccountNumber());
    return atmService.transfer(accountOrigin, accountDestiny, transferRequest.getDebit());
  }

  public Page<MovementDTO> findAllMovements(Pageable pageable) {
    return movementService.findAllMovements(pageable);
  }

  public Page<MovementDTO> findAllMovementsByAccount(Long account, Pageable pageable) {
    return movementService.findAllMovementsByAccount(account, pageable);
  }
}
