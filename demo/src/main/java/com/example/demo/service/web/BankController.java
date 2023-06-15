package com.example.demo.service.web;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.core.account.web.AccountDTO;
import com.example.demo.core.customer.web.CustomerDTO;
import com.example.demo.core.movement.web.MovementDTO;
import com.example.demo.error.EntityNotFoundException;
import com.example.demo.service.BankService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/bank")
public class BankController
{
  private final BankService bankService;

  BankController(BankService bankService) {
    this.bankService = bankService;
  }

  @GetMapping
  @ResponseBody
  public Page<CustomerDTO> getAllCustomers(
      @PageableDefault(sort = "customerId", direction = Sort.Direction.ASC) Pageable pageable)
  {
    return bankService.findAllCustomers(pageable);
  }

  @GetMapping("/customers/{id}")
  @ResponseBody
  public AccountDTO getAccountBycustomer(@PathVariable Long id) {
    try {
      return bankService.getAccountByCustomer(id);
    }
    catch (EntityNotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }

  }

  @PutMapping("/transactional/deposit")
  @ResponseStatus(HttpStatus.OK)
  public BigDecimal depositFunds(@RequestBody @Valid DepositRequest depositRequest) {
    return bankService.depositFunds(depositRequest);
  }

  @PutMapping("/transactional/withdraw")
  @ResponseStatus(HttpStatus.OK)
  public BigDecimal whithdrawFunds(@RequestBody @Valid WithdrawRequest withdrawRequest) {
    return bankService.withdrawFunds(withdrawRequest);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/transactional/transfer")
  public BigDecimal transferFunds(@RequestBody @Valid TransferRequest transferRequest) {
    return bankService.transferFunds(transferRequest);
  }

  @GetMapping("/movements")
  @ResponseBody
  public Page<MovementDTO> getAllMovements(
      @PageableDefault(sort = "timeStamp", direction = Sort.Direction.ASC) Pageable pageable)
  {
    return bankService.findAllMovements(pageable);
  }

  @GetMapping("/movements/{accountId}")
  @ResponseBody
  public Page<MovementDTO> getAllMovements(
      @PathVariable Long accountId,
      @PageableDefault(sort = "timeStamp", direction = Sort.Direction.ASC) Pageable pageable)
  {
    return bankService.findAllMovementsByAccount(accountId, pageable);
  }

}
