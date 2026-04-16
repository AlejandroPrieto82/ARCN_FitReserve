package com.fitreserve.infrastructure.persistence.repository;

import com.fitreserve.infrastructure.persistence.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, UUID> {

    List<ReservationEntity> findByUserId(UUID userId);

    boolean existsByUserIdAndClassId(UUID userId, UUID classId);
}