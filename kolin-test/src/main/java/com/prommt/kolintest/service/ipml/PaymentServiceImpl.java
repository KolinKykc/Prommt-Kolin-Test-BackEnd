package com.prommt.kolintest.service.ipml;

import com.prommt.kolintest.exception.ObjectNotFoundException;
import com.prommt.kolintest.model.Payment;
import com.prommt.kolintest.repository.PaymentRepository;
import com.prommt.kolintest.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        payment.setStatus("unpaid");
        payment.setCreated_date( new Date());
        payment.setPaid_date( null);
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public void updatePayment(Long id, Payment payment) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);
        if (!existingPayment.isPresent())
            throw new ObjectNotFoundException("Payment not found");

        Payment newPayment = existingPayment.get();

        newPayment.setCreated_date(existingPayment.get().getCreated_date());
        newPayment.setPayer_email(payment.getPayer_email());
        newPayment.setStatus("paid");
        newPayment.setCurrency(payment.getCurrency());
        newPayment.setAmount(payment.getAmount());
        newPayment.setPaid_date(new Date());

        paymentRepository.save(newPayment);

    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);

    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);

        if (!payment.isPresent())
            throw new ObjectNotFoundException("Payment not found");

        return payment;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
