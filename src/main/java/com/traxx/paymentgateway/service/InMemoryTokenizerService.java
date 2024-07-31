// InMemoryTokenizerService.java
package com.traxx.paymentgateway.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class InMemoryTokenizerService<T> implements TokenizerService<T> {

  private final Map<String, T> tokenStore = new HashMap<>();

  @Override
  public String tokenize(T data) {
    String token = UUID.randomUUID().toString();
    tokenStore.put(token, data);
    return token;
  }

  @Override
  public T detokenize(String token) {
    return tokenStore.get(token);
  }
}
