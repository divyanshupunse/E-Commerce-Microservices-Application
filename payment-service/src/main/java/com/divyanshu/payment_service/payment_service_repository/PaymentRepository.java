package com.divyanshu.payment_service.payment_service_repository;

import com.divyanshu.payment_service.payment_service_model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {

     Payment findByOrderId(int orderId);

}
