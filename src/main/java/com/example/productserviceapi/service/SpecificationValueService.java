package com.example.productserviceapi.service;

import com.example.productserviceapi.model.entity.SpecificationValueNodeEntity;
import com.example.productserviceapi.repository.SpecificationValueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpecificationValueService {

    private final SpecificationValueRepository specificationValueRepository;

    @Cacheable(value = "specification_value", key = "#id", cacheManager = "oneMinuteCacheManager", unless = "#result==null")
    public SpecificationValueNodeEntity getSpecificationValueNodeById(final UUID id){
        Optional<SpecificationValueNodeEntity> specificationValueNodeEntity = specificationValueRepository.findById(id);
        if (specificationValueNodeEntity.isEmpty()) {
            return null;
        }
        return specificationValueNodeEntity.get();
    }

}