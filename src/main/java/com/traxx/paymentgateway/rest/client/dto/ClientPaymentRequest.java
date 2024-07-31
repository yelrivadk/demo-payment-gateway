package com.traxx.paymentgateway.rest.client.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientPaymentRequest {

  @NotBlank(message = "Transaction reference is required")
  private String transactionReference;

  @NotNull(message = "Amount is required")
  @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
  private BigDecimal amount;

  @NotBlank(message = "Currency is required")
  @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a valid ISO 4217 currency code")
  private String currency;

  @NotBlank(message = "Card number is required")
  @Pattern(regexp = "^[0-9]{16}$", message = "Card number must be 16 digits")
  private String cardNumber;
}
