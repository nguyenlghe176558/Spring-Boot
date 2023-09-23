package com.example.apidemo.SpringBoot.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.apidemo.SpringBoot.model.Product;
import com.example.apidemo.SpringBoot.repository.ProductRepository;

@Configuration
public class Database {
    // logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                // Product productA = new Product("iPhone 15", 2023, 999.9, "apple.com");
                // Product productB = new Product("MacBook Air", 2022, 1099.9, "apple.com");
                // logger.info("insert database " + productRepository.save(productA));
                // logger.info("insert database " + productRepository.save(productB));
            }
        };
    }

}
