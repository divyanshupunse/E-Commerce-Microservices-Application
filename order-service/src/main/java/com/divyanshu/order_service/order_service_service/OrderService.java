package com.divyanshu.order_service.order_service_service;

import com.divyanshu.order_service.order_service_Feign.PaymentClient;
import com.divyanshu.order_service.order_service_Feign.ProductClient;
import com.divyanshu.order_service.order_service_model.Order;
import com.divyanshu.order_service.order_service_model.OrderStatus;
import com.divyanshu.order_service.order_service_repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private PaymentClient paymentClient;

    public Order placeOrder(Order order){
        var product = productClient.getProductById(order.getProductId());
        if (product == null) {
            throw new RuntimeException("Product with ID " + order.getProductId() + " does not exist");
        }
        if (product.getProductStock() < order.getOrderStock()) {
            throw new RuntimeException("Insufficient stock: available " + product.getProductStock());
        }

        int totalPrice = product.getProductPrice() * order.getOrderStock();
        order.setOrderPrice(totalPrice);
        order.setOrderStatus(OrderStatus.PENDING);

        Order saveOrder=repository.save(order);

        // Payment is pending

        return repository.save(saveOrder);
    }

    public List<Order> getOrderByUserId(int userID){
         return repository.findByUserId(userID);
    }
    public List<Order> viewOrders(){
        return repository.findAll();
    }
    public Order updateOrderStatus(int orderId , OrderStatus status){
        Optional<Order> order=repository.findById(orderId);
        if (!order.isPresent()){
            throw new RuntimeException("Order not found");
        }
        Order order1=order.get();
        order1.setOrderStatus(status);
        return repository.save(order1);
    }



}
