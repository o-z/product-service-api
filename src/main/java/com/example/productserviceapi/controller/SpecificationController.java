package com.example.productserviceapi.controller;

import com.example.productserviceapi.model.dto.request.SaveSpecificationRequest;
import com.example.productserviceapi.model.entity.SpecificationKeyNodeEntity;
import com.example.productserviceapi.service.SpecificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/specifications")
@RequiredArgsConstructor
@Slf4j
public class SpecificationController {
    private final SpecificationService specificationService;


    @GetMapping("/{id}")
    public ResponseEntity<SpecificationKeyNodeEntity> getSpecificationById(@PathVariable final UUID id) {
        return ResponseEntity.ok(specificationService.getSpecificationById(id));
    }


    @PostMapping
    public ResponseEntity<SpecificationKeyNodeEntity> saveSpecification(@RequestBody final SaveSpecificationRequest request) {
        return ResponseEntity.ok(specificationService.saveSpecification(request));
    }
}