package com.fitreserve.infrastructure.persistence.repository;

import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.valueobject.ClassId;
import com.fitreserve.domain.valueobject.ReservationId;
import com.fitreserve.domain.valueobject.UserId;
import com.fitreserve.infrastructure.persistence.entity.ReservationEntity;
import com.fitreserve.infrastructure.persistence.mapper.ReservationMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository jpaRepository;
    private final ReservationMapper mapper;

    public ReservationRepositoryImpl(ReservationJpaRepository jpaRepository,
                                     ReservationMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity entity = mapper.toEntity(reservation);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Reservation> findById(ReservationId reservationId) {
        return jpaRepository.findById(reservationId.getValue())
                .map(mapper::toDomain);
    }

    @Override
    public List<Reservation> findByUserId(UserId userId) {
        return jpaRepository.findByUserId(userId.getValue())
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByUserIdAndClassId(UserId userId, ClassId classId) {
        return jpaRepository.existsByUserIdAndClassId(
                userId.getValue(),
                classId.getValue()
        );
    }
}