package com.example.productserviceapi.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.io.Serializable;

@Data
@RelationshipProperties
@Builder
public class CategoryToSpecificationRelation implements Serializable {

    @RelationshipId
    private Long id;

    private final Boolean required;

    @TargetNode
    private final SpecificationKeyNodeEntity specificationNode;
}
