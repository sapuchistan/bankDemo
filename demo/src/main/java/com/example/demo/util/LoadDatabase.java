package com.example.demo.util;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.core.account.Account;
import com.example.demo.core.account.AccountNumber;
import com.example.demo.core.account.AccountNumberRepo;
import com.example.demo.core.account.AccountRepo;
import com.example.demo.core.customer.Customer;
import com.example.demo.core.customer.CustomerRepo;

@Configuration
public class LoadDatabase
{

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(
      CustomerRepo customerRepo,
      AccountRepo accountRepo,
      AccountNumberRepo accountNumberRepo)
  {

    BigDecimal balance = BigDecimal.valueOf(0.0D);
    AccountNumber gen = new AccountNumber();
    AccountNumber one = accountNumberRepo.save(gen);

    Customer daffy = new Customer();
    Account accountDaffy = new Account();
    daffy.setFirstName("Daffy");
    daffy.setLastName("Duck");
    accountDaffy.setAccountCountryNumber(57L);
    accountDaffy.setAccountCityNumber(1L);
    accountDaffy.setAccountOfficeNumber(101L);
    accountDaffy.setCustomer(daffy);
    accountDaffy.setBalance(balance);
    accountDaffy.setAccountNumber(one);

    Customer elmer = new Customer();
    gen = new AccountNumber();
    one = accountNumberRepo.save(gen);
    Account accountElmer = new Account();
    elmer.setFirstName("Elmer");
    elmer.setLastName("Fudd");
    accountElmer.setAccountCountryNumber(57L);
    accountElmer.setAccountCityNumber(1L);
    accountElmer.setAccountOfficeNumber(101L);
    accountElmer.setCustomer(elmer);
    accountElmer.setBalance(balance);
    accountElmer.setAccountNumber(one);

    Customer wile = new Customer();
    Account accountWile = new Account();
    gen = new AccountNumber();
    one = accountNumberRepo.save(gen);

    wile.setFirstName("Wile e.");
    wile.setLastName("Coyote");
    accountWile.setAccountCountryNumber(57L);
    accountWile.setAccountCityNumber(1L);
    accountWile.setAccountOfficeNumber(101L);
    accountWile.setCustomer(wile);
    accountWile.setBalance(balance);
    accountWile.setAccountNumber(one);

    return args -> {
      log.info("Preloading daffy" + customerRepo.save(daffy));
      log.info("Preloading daffy" + accountRepo.save(accountDaffy));

      log.info("Preloading elmer" + customerRepo.save(elmer));
      log.info("Preloading elmer" + accountRepo.save(accountElmer));

      log.info("Preloading wile" + customerRepo.save(wile));
      log.info("Preloading wile" + accountRepo.save(accountWile));
    };
  }
}
