package com.example.demo.service.web;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class WithdrawRequest
{
  @NotBlank
  @Pattern(regexp = "\\d")
  private String idCustomer;

  @NotBlank
  @DecimalMin(value = "0.0", inclusive = false)
  @Digits(integer = 10, fraction = 3)
  @DecimalMax(value = "9999999999.999", message = "The decimal value can not be more than 9999999999.999")
  private String debit;

  public Long getIdCustomer() {
    return Long.parseLong(idCustomer);
  }

  public BigDecimal getDebit() {
    return new BigDecimal(debit);
  }

}
