package com.divyanshu.order_service.order_service_dto;

public class ProductDto {
    private int productId;
    private String productName;
    private int productPrice;
    private int productStock;
    private String productCategory;

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getProductPrice() { return productPrice; }
    public void setProductPrice(int productPrice) { this.productPrice = productPrice; }

    public int getProductStock() { return productStock; }
    public void setProductStock(int productStock) { this.productStock = productStock; }

    public String getProductCategory() { return productCategory; }
    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }
}
