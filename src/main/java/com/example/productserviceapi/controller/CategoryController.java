package com.example.productserviceapi.controller;

import com.example.productserviceapi.model.dto.request.SaveCategoryRequest;
import com.example.productserviceapi.model.entity.CategoryNodeEntity;
import com.example.productserviceapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/{id}")
    public ResponseEntity<CategoryNodeEntity> getCategoryById(@PathVariable final UUID id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<Map<UUID, CategoryNodeEntity>> getAllCategories(@RequestParam("deepest") Boolean deepest,
                                                                          @RequestParam("page") Integer page,
                                                                          @RequestParam("Size") Integer size) {
        return ResponseEntity.ok(categoryService.getAllCategories(deepest, page, size));
    }


    @PostMapping
    public ResponseEntity<CategoryNodeEntity> saveCategory(@RequestBody final SaveCategoryRequest request) {
        return ResponseEntity.ok(categoryService.saveCategory(request));
    }

}