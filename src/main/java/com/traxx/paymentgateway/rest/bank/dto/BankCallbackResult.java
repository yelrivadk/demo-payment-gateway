package com.traxx.paymentgateway.rest.bank.dto;

import lombok.Data;

@Data
public class BankCallbackResult {
  private String status;
  private String transactionId;
  private String message;
}
