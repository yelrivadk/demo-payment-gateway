package com.traxx.paymentgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication
public class PaymentGatewayApplication {
  @Value("${server.port}")
  private int serverPort;

  public static void main(String[] args) {
    SpringApplication.run(PaymentGatewayApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void printApplicationUrl() {
    System.out.println("Application started at: http://localhost:" + serverPort);
  }
}
