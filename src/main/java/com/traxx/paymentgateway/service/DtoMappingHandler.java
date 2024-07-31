// DtoMappingHandler.java
package com.traxx.paymentgateway.service;

import com.traxx.paymentgateway.client.model.BankPaymentRequest;
import com.traxx.paymentgateway.rest.client.dto.ClientPaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DtoMappingHandler {

  public BankPaymentRequest process(ClientPaymentRequest request) {
    return mapToBankPaymentRequest(request);
  }

  private BankPaymentRequest mapToBankPaymentRequest(ClientPaymentRequest clientPaymentRequest) {
    var request = new BankPaymentRequest();
    request.setTransactionReference(clientPaymentRequest.getTransactionReference());

    var instruction = new BankPaymentRequest.Instruction();
    var value = new BankPaymentRequest.Instruction.Value();
    value.setAmount(clientPaymentRequest.getAmount());
    value.setCurrency(clientPaymentRequest.getCurrency());
    instruction.setValue(value);

    var paymentInstrument = new BankPaymentRequest.Instruction.PaymentInstrument();
    paymentInstrument.setCardNumber(clientPaymentRequest.getCardNumber());
    instruction.setPaymentInstrument(paymentInstrument);

    request.setInstruction(instruction);

    return request;
  }
}