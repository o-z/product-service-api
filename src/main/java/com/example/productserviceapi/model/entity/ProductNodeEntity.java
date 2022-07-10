package com.example.productserviceapi.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@Node
public class ProductNodeEntity implements Serializable {

    @Id
    @GeneratedValue(GeneratedValue.UUIDGenerator.class)
    private final UUID id ;

    private String title ;

    @CompositeProperty
    private final Map<String, String> imageUrlMap;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    private Boolean active;

    private Long totalStockQuantity;

    private BigDecimal price;

    @Relationship(type="CREATED_BY", direction = Relationship.Direction.OUTGOING)
    private final UserNodeEntity userNodeEntity;

    @Relationship(type="CATEGORY", direction = Relationship.Direction.OUTGOING)
    private final CategoryNodeEntity categoryNodeEntity;

    @Relationship(type="PRODUCT_SPECIFICATION_GROUP", direction = Relationship.Direction.OUTGOING)
    private final List<ProductToSpecificationGroupRelation> productToSpecificationGroupRelations;

}
