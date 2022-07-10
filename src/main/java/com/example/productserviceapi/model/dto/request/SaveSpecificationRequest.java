package com.example.productserviceapi.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SaveSpecificationRequest {

    private String name;
    private List<String> values;
}
