package com.fitreserve.infrastructure.persistence.repository;

import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.infrastructure.persistence.entity.ReservationEntity;
import com.fitreserve.infrastructure.persistence.mapper.ReservationMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository jpaRepository;

    public ReservationRepositoryImpl(ReservationJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity entity = ReservationMapper.toEntity(reservation);
        return ReservationMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Reservation> findById(ReservationId reservationId) {
        return jpaRepository.findById(reservationId.getValue())
                .map(ReservationMapper::toDomain);
    }

    @Override
    public List<Reservation> findByUserId(UserId userId) {
        return jpaRepository.findByUserId(userId.getValue())
                .stream()
                .map(ReservationMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByUserIdAndClassId(UserId userId, ClassId classId) {
        return jpaRepository.existsByUserIdAndClassId(
                userId.getValue(),
                classId.getValue()
        );
    }
}