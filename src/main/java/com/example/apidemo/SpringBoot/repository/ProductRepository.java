package com.example.apidemo.SpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apidemo.SpringBoot.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
