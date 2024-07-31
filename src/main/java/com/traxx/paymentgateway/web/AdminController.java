package com.traxx.paymentgateway.web;

import com.traxx.paymentgateway.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
  private final PaymentService paymentService;

  @GetMapping("/transactions")
  public String listTransactions(Model model) {
    var transactions = paymentService.getTransactionHistory();
    model.addAttribute("transactions", transactions);
    return "transaction-history";
  }
}
