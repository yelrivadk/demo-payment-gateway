package com.traxx.paymentgateway.client.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankPaymentRequest {
  private String transactionReference;
  private Instruction instruction;

  @Data
  public static class Instruction {
    private Value value;
    private PaymentInstrument paymentInstrument;

    @Data
    public static class Value {
      private BigDecimal amount;
      private String currency;
    }

    @Data
    public static class PaymentInstrument {
      private String cardNumber;
    }
  }
}
