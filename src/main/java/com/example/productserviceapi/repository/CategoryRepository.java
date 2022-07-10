package com.example.productserviceapi.repository;

import com.example.productserviceapi.model.entity.CategoryNodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Map;
import java.util.UUID;

public interface CategoryRepository extends Neo4jRepository<CategoryNodeEntity, UUID> {

    Page<CategoryNodeEntity> getCategoryNodeEntitiesByDeepest(Boolean deepest, Pageable pageable);

    @Query(value = "MATCH (c:CategoryNodeEntity), (p:CategoryNodeEntity) " +
            "WHERE c.id = $childId AND p.id = $parentId " +
            "CREATE (c)-[r:CATEGORY_CHILD_OF]->(p)")
    void createRelationBetweenChildToParent(UUID childId,UUID parentId);

    @Query(value = "MATCH (c:CategoryNodeEntity), (s:SpecificationKeyNodeEntity) " +
            "WHERE c.id = $categoryId AND s.id in :#{#specificationKeys.keySet()} " +
            "CREATE (c)-[r:CATEGORY_SPECIFICATION {required: :#{#specificationKeys.size} }]->(s)")
    void createRelationBetweenSpecification(UUID categoryId, Map<String,Boolean> specificationKeys);

}