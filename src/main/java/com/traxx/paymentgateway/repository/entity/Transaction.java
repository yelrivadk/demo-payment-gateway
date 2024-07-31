package com.traxx.paymentgateway.repository.entity;

import com.traxx.paymentgateway.client.model.BankPaymentResponse;
import jakarta.persistence.*;
import lombok.Data;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String transactionReference;
  private BigDecimal amount;
  private String currency;
  private String cardNumber;

  @Column(unique = true)
  private String transactionId;
  @Enumerated(EnumType.STRING)
  private BankPaymentResponse.Status status;
  private String message;

  @CreatedDate
  private LocalDateTime createdAt;
  @LastModifiedDate
  private LocalDateTime updatedAt;

  @PreUpdate
  @PrePersist
  public void encryptSensitiveData() {
    var textEncryptor = new AES256TextEncryptor();
    textEncryptor.setPassword("secretkey");
    this.cardNumber = textEncryptor.encrypt(this.cardNumber);
  }

  @PostLoad
  public void decryptSensitiveData() {
    var textEncryptor = new AES256TextEncryptor();
    textEncryptor.setPassword("secretkey");
    this.cardNumber = textEncryptor.decrypt(this.cardNumber);
  }
}