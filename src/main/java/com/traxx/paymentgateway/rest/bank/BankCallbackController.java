// BankCallbackController.java
package com.traxx.paymentgateway.rest.bank;

import com.traxx.paymentgateway.rest.bank.dto.BankCallbackResult;
import com.traxx.paymentgateway.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bank-callback")
@RequiredArgsConstructor
public class BankCallbackController {

  private final PaymentService paymentService;

  @PostMapping("/result")
  public ResponseEntity<String> handleBankCallback(@RequestBody BankCallbackResult result) {
    paymentService.handle3DSCallback(result);
    String resultUrl = "http://localhost:8000/result?transactionId=" + result.getTransactionId();
    return ResponseEntity.ok(resultUrl);
  }
}
