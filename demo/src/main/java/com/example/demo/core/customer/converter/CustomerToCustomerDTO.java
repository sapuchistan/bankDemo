package com.example.demo.core.customer.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.core.customer.Customer;
import com.example.demo.core.customer.web.CustomerDTO;

@Component
public class CustomerToCustomerDTO
    implements Converter<Customer, CustomerDTO>
{
  @Override
  public CustomerDTO convert(Customer customer) {
    CustomerDTO dto = new CustomerDTO();
    dto.setId(customer.getCustomerId());
    dto.setFirstName(customer.getFirstName());
    dto.setLastName(customer.getLastName());
    return dto;
  }

}
