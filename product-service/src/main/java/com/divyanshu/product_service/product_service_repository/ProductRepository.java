package com.divyanshu.product_service.product_service_repository;

import com.divyanshu.product_service.product_service_model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product ,Integer> {

}
