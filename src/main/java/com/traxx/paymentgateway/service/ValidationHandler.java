// ValidationHandler.java
package com.traxx.paymentgateway.service.handler;

import com.traxx.paymentgateway.rest.client.dto.ClientPaymentRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ValidationHandler {

  private final Validator validator;

  public ClientPaymentRequest process(ClientPaymentRequest request) {
    Set<ConstraintViolation<ClientPaymentRequest>> violations = validator.validate(request);
    if (!violations.isEmpty()) {
      throw new IllegalArgumentException("Validation failed: " + violations);
    }
    return request;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public static class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
      super(message);
    }
  }
}
