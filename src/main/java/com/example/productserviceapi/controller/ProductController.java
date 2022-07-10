package com.example.productserviceapi.controller;

import com.example.productserviceapi.model.dto.request.SaveProductRequest;
import com.example.productserviceapi.model.entity.ProductNodeEntity;
import com.example.productserviceapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<ProductNodeEntity>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @PostMapping()
    public ResponseEntity<ProductNodeEntity> saveProduct(@RequestBody final SaveProductRequest request) {
        return ResponseEntity.ok(productService.saveProduct(request));
    }

}