package com.example.productserviceapi.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@Node
public class UserNodeEntity implements Serializable {

    @Id
    @GeneratedValue(GeneratedValue.UUIDGenerator.class)
    private final UUID id;

    private String name;

    private String surname;


}
