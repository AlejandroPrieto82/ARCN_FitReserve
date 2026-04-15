package com.fitreserve.infrastructure.persistence.repository;

import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.valueobject.ClassId;
import com.fitreserve.infrastructure.persistence.entity.GymClassEntity;
import com.fitreserve.infrastructure.persistence.mapper.GymClassMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GymClassRepositoryImpl implements GymClassRepository {

    private final GymClassJpaRepository jpaRepository;

    public GymClassRepositoryImpl(GymClassJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public GymClass save(GymClass gymClass) {
        GymClassEntity entity = GymClassMapper.toEntity(gymClass);
        return GymClassMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<GymClass> findById(ClassId classId) {
        return jpaRepository.findById(classId.getValue())
                .map(GymClassMapper::toDomain);
    }

    @Override
    public List<GymClass> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(GymClassMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(ClassId classId) {
        jpaRepository.deleteById(classId.getValue());
    }
}