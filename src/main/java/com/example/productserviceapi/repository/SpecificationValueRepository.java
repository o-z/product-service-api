package com.example.productserviceapi.repository;

import com.example.productserviceapi.model.entity.SpecificationValueNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface SpecificationValueRepository extends Neo4jRepository<SpecificationValueNodeEntity, UUID> {
}