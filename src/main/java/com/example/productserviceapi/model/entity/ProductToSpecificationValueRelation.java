package com.example.productserviceapi.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Data
@RelationshipProperties
@Builder
public class ProductToSpecificationValueRelation {

    @RelationshipId
    private Long id;

    @TargetNode
    private SpecificationValueNodeEntity specificationNode;
}
