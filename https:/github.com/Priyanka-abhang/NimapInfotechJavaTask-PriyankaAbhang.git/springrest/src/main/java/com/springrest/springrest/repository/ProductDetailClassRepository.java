package com.springrest.springrest.repository;


import com.springrest.springrest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailClassRepository extends JpaRepository<Product, Long> {
}
