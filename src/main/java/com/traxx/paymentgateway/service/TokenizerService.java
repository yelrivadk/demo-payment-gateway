// TokenizerService.java
package com.traxx.paymentgateway.service;

public interface TokenizerService<T> {

  String tokenize(T data);

  T detokenize(String token);
}
