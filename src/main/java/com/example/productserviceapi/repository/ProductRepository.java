package com.example.productserviceapi.repository;

import com.example.productserviceapi.model.entity.ProductNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface ProductRepository extends Neo4jRepository<ProductNodeEntity, UUID> {


}
