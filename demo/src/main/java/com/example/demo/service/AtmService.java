package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.core.account.Account;
import com.example.demo.core.account.AccountRepo;
import com.example.demo.core.movement.Movement;
import com.example.demo.core.movement.MovementRepo;
import com.example.demo.core.movement.MovementType;
import com.example.demo.error.AccountBalanceException;
import com.example.demo.util.MessageUtil;

@Service
public class AtmService
    implements Atm
{
  private final MovementRepo movementRepo;

  private final AccountRepo accountRepo;

  private final MessageUtil messageUtil;

  private ZoneId zid = ZoneId.of("US/Eastern");

  AtmService(MovementRepo movementRepo, MessageUtil messageUtil, AccountRepo accountRepo) {
    this.movementRepo = movementRepo;
    this.messageUtil = messageUtil;
    this.accountRepo = accountRepo;
  }

  @Override
  public BigDecimal deposit(Account account, BigDecimal credit, Boolean isTransfer) {

    if (credit.compareTo(BigDecimal.ZERO) > 0) {
      Movement movement = new Movement();
      BigDecimal tmpBalance = account.getBalance().add(credit);
      account.setBalance(tmpBalance);
      movement.setAccount(account);
      movement.setBalance(account.getBalance());
      movement.setCredit(credit);
      movement.setTimeStamp(LocalDateTime.now(zid));
      if (Boolean.TRUE.equals(isTransfer)) {
        movement.setMovementType(MovementType.transfer);
      }
      else {
        movement.setMovementType(MovementType.deposit);
      }
      accountRepo.save(account);
      movementRepo.save(movement);
      return tmpBalance;
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
    }
  }

  @Override
  public BigDecimal withDraw(Account account, BigDecimal debit, Boolean isTransfer) {
    Movement movement = new Movement();
    BigDecimal tmpBalance = account.getBalance();
    if (debit.compareTo(BigDecimal.ZERO) > 0 && tmpBalance.compareTo(debit) >= 0) {
      tmpBalance = account.getBalance().subtract(debit);
      account.setBalance(tmpBalance);
      movement.setAccount(account);
      movement.setBalance(account.getBalance());
      movement.setDebit(debit);
      movement.setTimeStamp(LocalDateTime.now(zid));
      if (Boolean.TRUE.equals(isTransfer)) {
        movement.setMovementType(MovementType.transfer);
      }
      else {
        movement.setMovementType(MovementType.withdraw);
      }
      accountRepo.save(account);
      movementRepo.save(movement);
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
    }
    return tmpBalance;
  }

  @Override
  public BigDecimal transfer(Account origin, Account destiny, BigDecimal debit) {
    BigDecimal tmpBalance = origin.getBalance();
    if (origin.getAccountNumber().equals(destiny.getAccountNumber())) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
    }
    try {
      tmpBalance = withDraw(origin, debit, true);
      deposit(destiny, debit, true);
    }
    catch (AccountBalanceException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
    }

    return tmpBalance;
  }

}
