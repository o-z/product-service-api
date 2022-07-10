package com.example.productserviceapi.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@Node
public class ProductSpecificationGroupNodeEntity implements Serializable {
    @Id
    @GeneratedValue(GeneratedValue.UUIDGenerator.class)
    private final UUID id;

    @CompositeProperty
    private final Map<String, String> imageUrlMap;

    private Long stockQuantity;

    private Long price;

    @Relationship(type="PRODUCT_SPECIFICATION", direction = Relationship.Direction.OUTGOING)
    private final List<ProductToSpecificationValueRelation> specificationValueRelations;

}
