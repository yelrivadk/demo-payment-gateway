package com.traxx.paymentgateway.rest.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientPaymentResponse {
  private String transactionId;
  private String status;
  private String message;

  public enum Status {
    secure,
    success,
    failure
  }
}
