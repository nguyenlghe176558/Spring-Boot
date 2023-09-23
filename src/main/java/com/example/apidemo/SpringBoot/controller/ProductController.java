package com.example.apidemo.SpringBoot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/Products")
public class ProductController {
    @GetMapping("")
    /// localhost:8080/api/v1/Products
    List<String> getAllProducts(){
        return List.of("iPhone", "iPad");
    }    
    
}
