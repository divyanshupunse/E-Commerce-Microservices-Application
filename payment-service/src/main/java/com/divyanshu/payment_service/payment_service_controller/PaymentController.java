package com.divyanshu.payment_service.payment_service_controller;

import com.divyanshu.payment_service.payment_service_model.Payment;
import com.divyanshu.payment_service.payment_service_service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;


    @GetMapping("/view/{orderId}")
    public Payment getPaymentDetailByOrderId(@PathVariable int orderId){
        Payment payment=service.getPaymentDetailByOrderId(orderId);
        if (payment==null){
            return null;
        }
       return payment;
    }

    @PostMapping
    public Payment processPayment(@RequestParam int orderId , @RequestParam int price){
        return service.paymentProcess(orderId,price);
    }
}
