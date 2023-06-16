package com.example.demo.core.account.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.core.account.Account;
import com.example.demo.core.account.web.AccountDTO;

@Component
public class AccountToAccountDTO
    implements Converter<Account, AccountDTO>
{

  @Override
  public AccountDTO convert(Account account) {
    AccountDTO dto = new AccountDTO();
    dto.setAccountCountryNumber(account.getAccountCountryNumber());
    dto.setAccountCityNumber(account.getAccountCityNumber());
    dto.setAccountOfficeNumber(account.getAccountOfficeNumber());
    dto.setAccountNumber(account.getAccountNumber().getAccountNumberId());
    dto.setBalance(account.getBalance());
    return dto;
  }

}
