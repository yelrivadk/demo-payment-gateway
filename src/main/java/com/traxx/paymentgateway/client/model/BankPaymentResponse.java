package com.traxx.paymentgateway.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankPaymentResponse {
  private String transactionId;
  private String status;
  private String message;

  public enum Status {
    secure,
    success,
    failure
  }
}
