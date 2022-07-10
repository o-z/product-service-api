package com.example.productserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;

@SpringBootApplication
@EnableNeo4jAuditing
@EnableCaching
public class ProductServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApiApplication.class, args);
    }

}
