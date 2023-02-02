package com.prommt.kolintest.service;

import com.prommt.kolintest.DTO.PaymentRequestDto;
import com.prommt.kolintest.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Payment createPayment(PaymentRequestDto paymentDto);

    Payment updatePayment(Long id, Payment payment);

    String deletePayment(Long id);

    Optional<Payment> getPaymentById(Long id);

    List<Payment> getAllPayments();
}
