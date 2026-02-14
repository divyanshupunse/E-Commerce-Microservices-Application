package com.divyanshu.order_service.order_service_Feign;

import com.divyanshu.order_service.order_service_dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("product-service")
public interface ProductClient  {

    @GetMapping("/products/view/{id}")
    ProductDto getProductById(@PathVariable("id") int id);
}
