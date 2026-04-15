package com.fitreserve.infrastructure.persistence.repository;

import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.valueobject.ClassId;
import com.fitreserve.infrastructure.persistence.entity.GymClassEntity;
import com.fitreserve.infrastructure.persistence.mapper.GymClassMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GymClassRepositoryImpl implements GymClassRepository {

    private final GymClassJpaRepository jpaRepository;
    private final GymClassMapper mapper;

    public GymClassRepositoryImpl(GymClassJpaRepository jpaRepository,
                                  GymClassMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public GymClass save(GymClass gymClass) {
        GymClassEntity entity = mapper.toEntity(gymClass);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<GymClass> findById(ClassId classId) {
        return jpaRepository.findById(classId.getValue())
                .map(mapper::toDomain);
    }

    @Override
    public List<GymClass> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(ClassId classId) {
        jpaRepository.deleteById(classId.getValue());
    }
}