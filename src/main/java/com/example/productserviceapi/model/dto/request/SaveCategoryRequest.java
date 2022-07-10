package com.example.productserviceapi.model.dto.request;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class SaveCategoryRequest {

    private String name;
    private UUID parentCategoryId;
    private Boolean deepest;
    private Map<String,Boolean> specificationIdAndRequiredStatus = new HashMap<>();;


}
