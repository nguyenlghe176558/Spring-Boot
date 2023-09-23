package com.example.apidemo.SpringBoot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * Get all products
     * 
     * @return all products
     */
    /// localhost:8080/api/v1/Products
    List<Product> getAllProducts() {
        return repository.findAll();
    }

    /**
     * Get product's details
     * 
     * @param id the id of the product
     * @return product's details
     */
    @GetMapping("/{id}")
    // return an object with data, message, status
    ResponseEntity<ResponseObject> getDetailProduct(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query product successfully", foundProduct)) :

                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false", "Cannot find product with id = " + id, ""));
    }

    /**
     * Insert a new product using POST method
     * Postman: RAW, JSON
     * 
     * @param newProduct
     * @return a new product
     */
    @PostMapping("/insertProduct")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        // check if product already exists
        List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
        if (foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Fail", "Product already exists!", ""));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Ok", "Insert product successfully!", repository.save(newProduct)));
        }
    }

    /**
     * Upsert product using PUT method
     * @param newProduct to insert
     * @param id of product needs to update
     * @return new product or updates existing product
     */
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updatedProduct = repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setProductYear(newProduct.getProductYear());
                    product.setPrice(newProduct.getPrice());
                    product.setUrl(newProduct.getUrl());
                    return repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Update product successfully!", updatedProduct));
    }
    
    /**
     * Delete a product
     * @param id of the product
     * @return status code
     */
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok", "Delete product successfully!", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("Fail", "Product not found!", "")
        );
    }
}
