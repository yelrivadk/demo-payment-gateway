package com.traxx.paymentgateway.rest.client;

import com.traxx.paymentgateway.client.model.BankPaymentResponse;
import com.traxx.paymentgateway.rest.client.dto.ClientPaymentRequest;
import com.traxx.paymentgateway.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

  private final PaymentService paymentService;

  @PostMapping("/process")
  public ResponseEntity<BankPaymentResponse> processPayment(@RequestBody ClientPaymentRequest request) {
    BankPaymentResponse response = paymentService.processPayment(request);
    return ResponseEntity.ok(response);
  }
}
