package com.example.demo.core.account;

import org.springframework.stereotype.Service;

import com.example.demo.core.account.converter.AccountToAccountDTOConverter;
import com.example.demo.core.account.web.AccountDTO;
import com.example.demo.error.EntityNotFoundException;
import com.example.demo.util.MessageUtil;

@Service
public class AccountService
{
  private final AccountRepo accountRepo;

  private final MessageUtil messageUtil;

  private final AccountToAccountDTOConverter accountToAccountDTOConverter;

  AccountService(
      AccountRepo accountRepo,
      MessageUtil messageUtil,
      AccountToAccountDTOConverter accountToAccountDTOConverter)
  {
    this.accountRepo = accountRepo;
    this.messageUtil = messageUtil;
    this.accountToAccountDTOConverter = accountToAccountDTOConverter;
  }

  public AccountDTO getAccount(Long idAccount) {
    Account account = findAccountOrThrow(idAccount);
    return accountToAccountDTOConverter.convert(account);
  }

  public String getAccountNumber(Account account) {
    return "" + account.getAccountCountryNumber() + account.getAccountCityNumber() + account.getAccountOfficeNumber()
        + account.getAccountNumber();
  }

  private Account findAccountOrThrow(Long idAccount) {
    return accountRepo.findById(idAccount)
        .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("account.NotFound", idAccount)));
  }

  public Account findByAccountNumberOrThrow(Long accountNumber) {
    return accountRepo.findByAccountNumber(accountNumber)
        .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("account.NotFound", accountNumber)));
  }

  public Account findByCustomerOrThrow(Long idCustomer) {
    return accountRepo.findByCustomerUsingId(idCustomer)
        .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("customer.NotFound", idCustomer)));
  }

  public AccountDTO getAccountByCustomer(Long idCustomer) {
    Account account = findByCustomerOrThrow(idCustomer);
    return accountToAccountDTOConverter.convert(account);
  }
}
