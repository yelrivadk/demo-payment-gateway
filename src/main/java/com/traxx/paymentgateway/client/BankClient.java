package com.traxx.paymentgateway.client;

import com.traxx.paymentgateway.client.model.BankPaymentRequest;
import com.traxx.paymentgateway.client.model.BankPaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bank-client", url = "http://localhost:8888")
public interface BankClient {

  @PostMapping("/api/payment/process")
  BankPaymentResponse processPayment(@RequestBody BankPaymentRequest bankPaymentRequest);
}
