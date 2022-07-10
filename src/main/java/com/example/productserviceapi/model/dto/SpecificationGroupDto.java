package com.example.productserviceapi.model.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SpecificationGroupDto {

    private Long stockQuantity;
    private Long price;
    private Map<String, String> imageUrlMap;
    private List<SpecificationDto> specificationDtoList;
}
