package com.divyanshu.payment_service.payment_service_service;

import com.divyanshu.payment_service.payment_service_model.Payment;
import com.divyanshu.payment_service.payment_service_model.PaymentStatus;
import com.divyanshu.payment_service.payment_service_repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.divyanshu.payment_service.payment_service_model.PaymentStatus.SUCCESS;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Payment getPaymentDetailByOrderId(int orderId){
       return repository.findByOrderId(orderId);
    }

    public Payment paymentProcess(int orderId, int price) {
        Payment payment = new Payment(orderId, price, SUCCESS);
        return repository.save(payment);
    }

}
