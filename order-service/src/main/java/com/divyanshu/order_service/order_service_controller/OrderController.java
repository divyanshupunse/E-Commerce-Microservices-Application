package com.divyanshu.order_service.order_service_controller;

import com.divyanshu.order_service.order_service_Feign.ProductClient;
import com.divyanshu.order_service.order_service_model.Order;
import com.divyanshu.order_service.order_service_model.OrderStatus;
import com.divyanshu.order_service.order_service_service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private ProductClient productClient;

    @PostMapping("/place")
    public String placeOrder(@RequestBody Order order){
         service.placeOrder(order);
        return "Order added successfully";
    }

    @GetMapping("/view/{userId}")
    public List<Order> viewOrderById(@PathVariable int userId){
        return service.getOrderByUserId(userId);
    }

    @PutMapping("/update/{orderId}")
    public String updateOrderStatus(@PathVariable int orderId , @RequestParam OrderStatus status){
        service.updateOrderStatus(orderId,status);
        return "Order updated successfully";
    }

}
