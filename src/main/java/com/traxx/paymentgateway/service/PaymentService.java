package com.traxx.paymentgateway.service;

import com.traxx.paymentgateway.client.BankClient;
import com.traxx.paymentgateway.client.model.BankPaymentRequest;
import com.traxx.paymentgateway.client.model.BankPaymentResponse;
import com.traxx.paymentgateway.repository.TransactionRepository;
import com.traxx.paymentgateway.repository.entity.Transaction;
import com.traxx.paymentgateway.rest.bank.dto.BankCallbackResult;
import com.traxx.paymentgateway.rest.client.dto.ClientPaymentRequest;
import com.traxx.paymentgateway.service.handler.TokenizerHandler;
import com.traxx.paymentgateway.service.handler.ValidationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final BankClient bankClient;
  private final TransactionRepository transactionRepository;
  private final TokenizerHandler tokenizerHandler;
  private final ValidationHandler validationHandler;
  private final DtoMappingHandler dtoMappingHandler;

  public BankPaymentResponse processPayment(ClientPaymentRequest clientPaymentRequest) {
    // Manually call each handler in sequence
    ClientPaymentRequest tokenizedRequest = tokenizerHandler.process(clientPaymentRequest);
    ClientPaymentRequest validatedRequest = validationHandler.process(tokenizedRequest);
    BankPaymentRequest bankPaymentRequest = dtoMappingHandler.process(validatedRequest);

    // Process payment
    var response = bankClient.processPayment(bankPaymentRequest);

    // Save transaction details
    var transaction = new Transaction();
    transaction.setTransactionReference(bankPaymentRequest.getTransactionReference());
    transaction.setStatus(BankPaymentResponse.Status.valueOf(response.getStatus().toLowerCase()));
    transaction.setMessage(response.getMessage());
    transaction.setAmount(bankPaymentRequest.getInstruction().getValue().getAmount());
    transaction.setCurrency(bankPaymentRequest.getInstruction().getValue().getCurrency());
    transaction.setCardNumber(bankPaymentRequest.getInstruction().getPaymentInstrument().getCardNumber());

    transactionRepository.save(transaction);

    return response;
  }

  public void handle3DSCallback(BankCallbackResult callbackData) {
    var transaction = transactionRepository.findByTransactionId(callbackData.getTransactionId());
    if (transaction != null) {
      transaction.setMessage(callbackData.getMessage());
      transaction.setStatus(BankPaymentResponse.Status.valueOf(callbackData.getStatus()));
      transactionRepository.save(transaction);
    }
  }

  public List<Transaction> getTransactionHistory() {
    return transactionRepository.findAll();
  }

  public Transaction findByTransactionId(String transactionId) {
    return transactionRepository.findByTransactionId(transactionId);
  }
}