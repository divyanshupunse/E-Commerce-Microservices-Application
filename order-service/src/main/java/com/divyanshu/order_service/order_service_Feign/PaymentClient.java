package com.divyanshu.order_service.order_service_Feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("payment-service")
public interface PaymentClient {
}
