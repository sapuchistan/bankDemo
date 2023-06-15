package com.example.demo.core.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.core.customer.converter.CustomerToCustomerDTO;
import com.example.demo.core.customer.web.CustomerDTO;

@Service
public class CustomerService
{

  private final CustomerRepo customerRepo;

  private final CustomerToCustomerDTO customerToCustomerDTO;

  CustomerService(CustomerRepo customerRepo, CustomerToCustomerDTO customerToCustomerDTO) {
    this.customerRepo = customerRepo;
    this.customerToCustomerDTO = customerToCustomerDTO;
  }

  public Page<CustomerDTO> findAllCustomers(Pageable pageable) {
    Page<Customer> customers = customerRepo.findAll(pageable);
    List<CustomerDTO> customerDTOList = new ArrayList<>();
    customers.forEach(customer -> {
      CustomerDTO customerDTO = customerToCustomerDTO.convert(customer);
      customerDTOList.add(customerDTO);
    });
    return new PageImpl<>(customerDTOList, pageable, customers.getTotalElements());
  }
}
