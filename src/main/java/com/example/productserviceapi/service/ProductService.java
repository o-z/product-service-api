package com.example.productserviceapi.service;

import com.example.productserviceapi.model.dto.request.SaveProductRequest;
import com.example.productserviceapi.model.entity.ProductNodeEntity;
import com.example.productserviceapi.model.entity.ProductSpecificationGroupNodeEntity;
import com.example.productserviceapi.model.entity.ProductToSpecificationGroupRelation;
import com.example.productserviceapi.model.entity.ProductToSpecificationValueRelation;
import com.example.productserviceapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final SpecificationValueService specificationValueService;

    @Transactional(readOnly = true)
    public Iterable<ProductNodeEntity> getAllProduct(){
        return productRepository.findAll();
    }

    @Transactional
    public ProductNodeEntity saveProduct(SaveProductRequest request){
        ProductNodeEntity productNodeEntity = ProductNodeEntity.builder()
                .title(request.getTitle())
                .imageUrlMap(request.getImageUrlMap())
                .active(true)
                .totalStockQuantity(request.getTotalStockQuantity())
                .price(request.getPrice())
                .userNodeEntity(userService.getUserById(request.getUserId()))
                .categoryNodeEntity(categoryService.getCategoryById(request.getCategoryId()))
                .productToSpecificationGroupRelations(request.getSpecificationGroupDtoList().stream()
                        .map(specificationGroupDto -> ProductToSpecificationGroupRelation.builder()
                                .specificationGroupNodeEntity(ProductSpecificationGroupNodeEntity.builder()
                                        .stockQuantity(specificationGroupDto.getStockQuantity())
                                        .price(specificationGroupDto.getPrice())
                                        .imageUrlMap(specificationGroupDto.getImageUrlMap())
                                        .specificationValueRelations(specificationGroupDto.getSpecificationDtoList().stream()
                                                .map(specificationDto ->
                                                        ProductToSpecificationValueRelation.builder()
                                                                .specificationNode(specificationValueService
                                                                        .getSpecificationValueNodeById(specificationDto.getId()))
                                                                .build()
                                                ).collect(Collectors.toList()))
                                        .build())
                                .build()).collect(Collectors.toList()))
                .build();


        return productRepository.save(productNodeEntity);
    }

}