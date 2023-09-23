package com.example.apidemo.SpringBoot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apidemo.SpringBoot.model.Product;
import com.example.apidemo.SpringBoot.model.ResponseObject;
import com.example.apidemo.SpringBoot.repository.ProductRepository;

@RestController
@RequestMapping(path = "api/v1/Products")
public class ProductController {
    // Dependency injection
    @Autowired
    private ProductRepository repository;

    @GetMapping("")
    /// localhost:8080/api/v1/Products
    List<Product> getAllProducts() {
        return repository.findAll();
    }

    // get detail product
    @GetMapping("/{id}")
    // return an object with data, message, status
    ResponseEntity<ResponseObject> getDetailProduct(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query product successfully", foundProduct)) :

                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false", "Cannot find product with id = " + id, ""));
    }

}
