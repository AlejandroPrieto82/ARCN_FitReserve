package com.fitreserve.infrastructure.persistence.repository;

import com.fitreserve.infrastructure.persistence.entity.GymClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GymClassJpaRepository extends JpaRepository<GymClassEntity, UUID> {
}