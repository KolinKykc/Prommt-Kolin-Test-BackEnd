package com.prommt.kolintest.service;

import com.prommt.kolintest.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Payment createPayment(Payment payment);

    void updatePayment(Long id, Payment payment);

    void deletePayment(Long id);

    Optional<Payment> getPaymentById(Long id);

    List<Payment> getAllPayments();
}
