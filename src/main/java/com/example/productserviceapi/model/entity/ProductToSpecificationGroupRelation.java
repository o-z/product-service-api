package com.example.productserviceapi.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Data
@RelationshipProperties
@Builder
public class ProductToSpecificationGroupRelation {

    @RelationshipId
    private Long id;

    @TargetNode
    private ProductSpecificationGroupNodeEntity specificationGroupNodeEntity;
}
