// TokenizerHandler.java
package com.traxx.paymentgateway.service.handler;

import com.traxx.paymentgateway.rest.client.dto.ClientPaymentRequest;
import com.traxx.paymentgateway.service.TokenizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenizerHandler {

  private final TokenizerService<String> tokenizerService;

  public ClientPaymentRequest process(ClientPaymentRequest request) {
    String tokenizedCardNumber = tokenizerService.tokenize(request.getCardNumber());
    request.setCardNumber(tokenizedCardNumber);
    return request;
  }
}
