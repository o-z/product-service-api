package com.example.productserviceapi.model.dto.request;

import com.example.productserviceapi.model.dto.SpecificationGroupDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class SaveProductRequest {

    private String title ;
    private Map<String, String> imageUrlMap;
    private Long totalStockQuantity;
    private BigDecimal price;
    private UUID userId;
    private UUID categoryId;
    private List<SpecificationGroupDto> specificationGroupDtoList;
}
