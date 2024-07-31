package com.traxx.paymentgateway.repository;

import com.traxx.paymentgateway.repository.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  Transaction findByTransactionId(String transactionId);
}
