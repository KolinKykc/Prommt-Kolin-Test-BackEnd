package com.prommt.kolintest.controller;

import com.prommt.kolintest.model.Payment;
import com.prommt.kolintest.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody Payment payment) {
        paymentService.createPayment(payment);
        return new ResponseEntity<>("created", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Payment>> getPaymentDetails(@PathVariable Long id) {
        return status(HttpStatus.OK).body(paymentService.getPaymentById(id));
    }
    @PostMapping("/{id}")
    public ResponseEntity<String> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        paymentService.updatePayment(id, payment);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return status(HttpStatus.OK).body(paymentService.getAllPayments());
    }

}
