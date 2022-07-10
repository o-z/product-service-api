package com.example.productserviceapi.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Node
public class CategoryNodeEntity implements Serializable {
    @Id
    @GeneratedValue(GeneratedValue.UUIDGenerator.class)
    private final UUID id;

    private String name;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    private Boolean deepest;

    @Relationship(type = "CATEGORY_CHILD_OF",direction = Relationship.Direction.OUTGOING)
    private final CategoryNodeEntity parentCategory;

    @Relationship(type = "CATEGORY_SPECIFICATION",direction = Relationship.Direction.OUTGOING)
    private final List<CategoryToSpecificationRelation> specifications;

}
