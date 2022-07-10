package com.example.productserviceapi.repository;

import com.example.productserviceapi.model.entity.SpecificationKeyNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface SpecificationRepository extends Neo4jRepository<SpecificationKeyNodeEntity, UUID> {
}