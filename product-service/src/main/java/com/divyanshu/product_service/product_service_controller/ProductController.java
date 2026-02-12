package com.divyanshu.product_service.product_service_controller;

import com.divyanshu.product_service._product_service_service.ProductService;
import com.divyanshu.product_service.product_service_model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/view")
    public List<Product> viewProduct(){
        return service.view();
    }
    @GetMapping("/view/{id}")
    public Optional<Product> viewById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product){
        service.addProduct(product);
        return "Product added successfully";
    }
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable int id , @RequestBody Product product){
       return service.updateProduct(id,product);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        service.deleteProduct(id);
        return "Product deleted successfully";
    }

}
