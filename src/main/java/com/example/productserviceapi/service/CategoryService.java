package com.example.productserviceapi.service;

import com.example.productserviceapi.model.dto.request.SaveCategoryRequest;
import com.example.productserviceapi.model.entity.CategoryNodeEntity;
import com.example.productserviceapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Cacheable(value = "category", key = "#id", cacheManager = "oneMinuteCacheManager", unless = "#result==null")
    @Transactional(readOnly = true)
    public CategoryNodeEntity getCategoryById(final UUID id) {
        Optional<CategoryNodeEntity> categoryNodeEntityOptional = categoryRepository.findById(id);
        if (categoryNodeEntityOptional.isEmpty()) {
            return null;
        }
        return categoryNodeEntityOptional.get();
    }

    @Transactional
    public Map<UUID, CategoryNodeEntity> getAllCategories(final Boolean deepest, Integer page, Integer size) {
        Page<CategoryNodeEntity> categoryNodeEntities = categoryRepository.getCategoryNodeEntitiesByDeepest(deepest, Pageable.ofSize(size).withPage(page));
        return categoryNodeEntities.stream().collect(Collectors.toMap(CategoryNodeEntity::getId, categoryNodeEntity -> categoryNodeEntity));
    }

    @Transactional
    @Cacheable(value = "category", key = "#result.id", cacheManager = "oneMinuteCacheManager", unless = "#result==null")
    public CategoryNodeEntity saveCategory(final SaveCategoryRequest request) {

        CategoryNodeEntity newEntity = CategoryNodeEntity.builder()
                .name(request.getName())
                .deepest(request.getDeepest())
                .build();

        CategoryNodeEntity categoryNodeEntity = categoryRepository.save(newEntity);
        if (request.getParentCategoryId() != null) {
            categoryRepository.createRelationBetweenChildToParent(categoryNodeEntity.getId(), request.getParentCategoryId());

        }
        if (!request.getSpecificationIdAndRequiredStatus().isEmpty()){

            categoryRepository.createRelationBetweenSpecification(categoryNodeEntity.getId(),request.getSpecificationIdAndRequiredStatus());
        }
        return categoryNodeEntity;
    }
}