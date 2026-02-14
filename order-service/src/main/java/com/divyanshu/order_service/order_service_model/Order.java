package com.divyanshu.order_service.order_service_model;

import jakarta.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int orderId;
    private String orderName;
    private int orderStock;
    private int orderPrice;

    private int userId;
    private int productId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  OrderStatus orderStatus;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderStock() {
        return orderStock;
    }

    public void setOrderStock(int orderStock) {
        this.orderStock = orderStock;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
