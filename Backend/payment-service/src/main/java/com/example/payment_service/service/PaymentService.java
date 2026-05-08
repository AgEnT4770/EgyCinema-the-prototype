package com.example.paymentservice.service;

import com.example.paymentservice.model.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Payment createPayment(Payment payment) {

        payment.setStatus("PAID");

        payment.setTransactionId(UUID.randomUUID().toString());

        payment.setCreatedAt(LocalDateTime.now());

        return repository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }
}