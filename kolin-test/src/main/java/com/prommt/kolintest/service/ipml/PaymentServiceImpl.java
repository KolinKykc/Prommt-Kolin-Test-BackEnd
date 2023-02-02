package com.prommt.kolintest.service.ipml;

import com.prommt.kolintest.DTO.PaymentRequestDto;
import com.prommt.kolintest.enumeration.Status;
import com.prommt.kolintest.exception.ObjectNotFoundException;
import com.prommt.kolintest.model.Payment;
import com.prommt.kolintest.repository.PaymentRepository;
import com.prommt.kolintest.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(PaymentRequestDto paymentDto) {
        log.info("Creating new payment");
        Payment newPayment = new Payment();
        newPayment.setStatus(Status.NOT_PAID);
        newPayment.setCreated_date( new Date());
        newPayment.setPayer_email(paymentDto.getPayer_email());
        newPayment.setCurrency(paymentDto.getCurrency());
        newPayment.setAmount(paymentDto.getAmount());
        newPayment.setPaid_date(null);
        paymentRepository.save(newPayment);
        return newPayment;
    }


    @Override
    public Payment updatePayment(Long id, Payment payment) {
        log.info("Updating payment with id {}", id);
        Optional<Payment> existingPayment = paymentRepository.findById(id);
        if (!existingPayment.isPresent())
            throw new ObjectNotFoundException("Payment not found");

        Payment newPayment = existingPayment.get();

        newPayment.setCreated_date(existingPayment.get().getCreated_date());
        newPayment.setPayer_email(payment.getPayer_email());
        newPayment.setStatus(Status.PAID);
        newPayment.setCurrency(payment.getCurrency());
        newPayment.setAmount(payment.getAmount());
        newPayment.setPaid_date(new Date());

        paymentRepository.save(newPayment);
        return newPayment;

    }

    @Override
    public String deletePayment(Long id) {
        log.info("Deleting payment with id {}", id);
        Optional<Payment> payment = paymentRepository.findById(id);
        if(payment.get().getStatus() == Status.NOT_PAID){
            paymentRepository.deleteById(id);
            return ("Payment Deleted");
        }
        else
            return "Payment already done";
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
