package com.divyanshu.product_service._product_service_service;

import com.divyanshu.product_service.product_service_model.Product;
import com.divyanshu.product_service.product_service_repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public List<Product> view() {
       return repository.findAll();
    }
    public Optional<Product> getById(int id){
        return repository.findById(id);
    }

    public void addProduct(Product product){
        repository.save(product);

    }

    public Product updateProduct(int id , Product product){

        Product check=repository.findById(id).orElseThrow(() -> new  RuntimeException("Wrong id"));
        check.setProductName(product.getProductName());
        check.setProductPrice(product.getProductPrice());
        check.setProductStock(product.getProductStock());
        check.setProductCategory(product.getProductCategory());

        return repository.save(check);
    }

    public void deleteProduct(int id){
        repository.deleteById(id);
    }


}
