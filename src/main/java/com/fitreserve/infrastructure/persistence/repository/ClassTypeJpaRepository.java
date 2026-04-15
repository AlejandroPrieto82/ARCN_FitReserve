package com.fitreserve.infrastructure.persistence.repository;

import com.fitreserve.infrastructure.persistence.entity.ClassTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClassTypeJpaRepository extends JpaRepository<ClassTypeEntity, UUID> {

    Optional<ClassTypeEntity> findByName(String name);
}