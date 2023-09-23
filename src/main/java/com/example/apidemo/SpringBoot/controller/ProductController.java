package com.example.apidemo.SpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apidemo.SpringBoot.model.Product;
import com.example.apidemo.SpringBoot.repository.ProductRepository;

@RestController
@RequestMapping(path = "api/v1/Products")
public class ProductController {
    // Dependency injection
    @Autowired
    private ProductRepository repository;

    @GetMapping("")
    /// localhost:8080/api/v1/Products
    List<Product> getAllProducts(){
        return repository.findAll();
    }


    // List<Product> getAllProducts() {
    //     return List.of(new Product(1L, "iPhone 15", 2023, 999.9, "apple.com"),
    //             new Product(2L, "MacBook Air", 2022, 1099.9, "apple.com"));
    // }

}
