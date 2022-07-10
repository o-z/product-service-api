package com.example.productserviceapi.service;

import com.example.productserviceapi.model.dto.request.SaveSpecificationRequest;
import com.example.productserviceapi.model.entity.SpecificationKeyNodeEntity;
import com.example.productserviceapi.model.entity.SpecificationValueNodeEntity;
import com.example.productserviceapi.repository.SpecificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpecificationService {

    private final SpecificationRepository specificationRepository;

    @Cacheable(value = "specification", key = "#id", cacheManager = "oneMinuteCacheManager", unless = "#result==null")
    @Transactional(readOnly = true)
    public SpecificationKeyNodeEntity getSpecificationById(final UUID id){
        Optional<SpecificationKeyNodeEntity> specificationKeyNodeEntity = specificationRepository.findById(id);
        if (specificationKeyNodeEntity.isEmpty()) {
            return null;
        }
        return specificationKeyNodeEntity.get();
    }

    @Cacheable(value = "specification", key = "#result.id", cacheManager = "oneMinuteCacheManager")
    @Transactional
    public SpecificationKeyNodeEntity saveSpecification(final SaveSpecificationRequest request) {

        SpecificationKeyNodeEntity specificationKeyNodeEntity = SpecificationKeyNodeEntity.builder()
                .name(request.getName())
                .specificationValueNodeEntities(
                        request.getValues().stream().map(value -> SpecificationValueNodeEntity.builder()
                                        .name(value)
                                        .build())
                                .collect(Collectors.toList())

                )
                .build();
        return specificationRepository.save(specificationKeyNodeEntity);
    }
}