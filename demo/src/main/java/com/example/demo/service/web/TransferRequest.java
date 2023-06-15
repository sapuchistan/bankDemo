package com.example.demo.service.web;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class TransferRequest
{
  @NotBlank
  @Pattern(regexp = "\\d")
  @Digits(integer = 10, fraction = 0)
  private String idCustomer;

  @NotBlank
  @DecimalMin(value = "0.0", inclusive = false)
  @Digits(integer = 10, fraction = 3)
  @DecimalMax(value = "9999999999.999", message = "The decimal value can not be more than 9999999999.999")
  private String debit;

  @NotBlank
  @Pattern(regexp = "\\d")
  @Digits(integer = 10, fraction = 0)
  private String accountNumber;

  public Long getIdCustomer() {
    return Long.parseLong(idCustomer);
  }

  public BigDecimal getDebit() {
    return new BigDecimal(debit);
  }

  public Long getAccountNumber() {
    return Long.parseLong(accountNumber);
  }

}
