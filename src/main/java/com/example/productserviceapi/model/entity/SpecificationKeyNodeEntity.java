package com.example.productserviceapi.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Node
public class SpecificationKeyNodeEntity implements Serializable {

    @Id
    @GeneratedValue(GeneratedValue.UUIDGenerator.class)
    private final UUID id;

    private String name;

    @Relationship(type="SPECIFICATION_VALUE", direction = Relationship.Direction.OUTGOING)
    private final List<SpecificationValueNodeEntity> specificationValueNodeEntities;
}
