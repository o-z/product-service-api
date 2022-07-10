package com.example.productserviceapi.repository;

import com.example.productserviceapi.model.entity.UserNodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface UserRepository extends Neo4jRepository<UserNodeEntity, UUID> {
    Page<UserNodeEntity> getUserNodeEntitiesBy(Pageable pageable);
}