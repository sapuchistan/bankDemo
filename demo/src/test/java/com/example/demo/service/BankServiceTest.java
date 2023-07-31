package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.core.account.Account;
import com.example.demo.core.account.AccountNumber;
import com.example.demo.core.account.AccountNumberRepo;
import com.example.demo.core.account.AccountRepo;
import com.example.demo.core.account.converter.AccountToAccountDTO;
import com.example.demo.core.account.web.AccountDTO;
import com.example.demo.core.customer.Customer;
import com.example.demo.core.customer.CustomerRepo;
import com.example.demo.core.customer.CustomerService;
import com.example.demo.core.customer.converter.CustomerToCustomerDTO;
import com.example.demo.core.customer.web.CustomerDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BankServiceTest
{

  @Autowired
  private BankService bankService;

  @InjectMocks
  private CustomerService customerService;

  @Autowired
  private CustomerRepo customerRepo;

  @Autowired
  private CustomerToCustomerDTO customerToCustomerDTO;

  @Autowired
  private AccountToAccountDTO accountToAccountDTO;

  @Autowired
  AccountNumberRepo accountNumberRepo;

  @Autowired
  AccountRepo accountRepo;

  @Test
  public void testFindAllCustomers() {
    PageRequest page = PageRequest.of(0, 10);
    Customer customer1 = new Customer("Daffy", "Duck");
    Customer customer2 = new Customer("Daffy", "Duck");
    List<CustomerDTO> customerDTOList = new ArrayList<>();
    CustomerDTO customerDTO1 = customerToCustomerDTO.convert(customer1);
    CustomerDTO customerDTO2 = customerToCustomerDTO.convert(customer2);
    customerDTOList.add(customerDTO1);
    customerDTOList.add(customerDTO2);
    customerRepo.save(customer1);
    customerRepo.save(customer2);
    Page<CustomerDTO> response = bankService.findAllCustomers(page);
    assertThat(response.getContent()).hasSize(6);
    customerRepo.delete(customer1);
    customerRepo.delete(customer2);
  }

  @Test
  public void testGetAccount() {
    Customer customer1 = new Customer("Daffy", "Duck");
    customerRepo.save(customer1);
    AccountNumber gen = new AccountNumber();
    AccountNumber one = accountNumberRepo.save(gen);
    Account account = new Account(customer1, 507L, 1L, 101L, one, new BigDecimal("0.00"));
    account = accountRepo.save(account);
    AccountDTO account1 = accountToAccountDTO.convert(account);
    AccountDTO response = bankService.getAccount(account.getAccountId());
    assertThat(response).usingRecursiveComparison().isEqualTo(account1);
  }

}
