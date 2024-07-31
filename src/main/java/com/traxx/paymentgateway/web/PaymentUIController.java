package com.traxx.paymentgateway.web;

import com.traxx.paymentgateway.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web")
@RequiredArgsConstructor
public class PaymentUIController {
  private final PaymentService paymentService;

  @GetMapping("/payment")
  public String showPaymentForm() {
    return "payment-form";
  }

  @GetMapping("/payment-result")
  public String showResultPage(@RequestParam("transactionId") String transactionId, Model model) {
    var transaction = paymentService.findByTransactionId(transactionId);
    model.addAttribute("transaction", transaction);
    return "payment-result";
  }
}
