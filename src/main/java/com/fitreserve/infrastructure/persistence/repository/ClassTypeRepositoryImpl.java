package com.fitreserve.infrastructure.persistence.repository;

import com.fitreserve.domain.model.ClassType;
import com.fitreserve.domain.repository.ClassTypeRepository;
import com.fitreserve.infrastructure.persistence.entity.ClassTypeEntity;
import com.fitreserve.infrastructure.persistence.mapper.ClassTypeMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClassTypeRepositoryImpl implements ClassTypeRepository {

    private final ClassTypeJpaRepository jpaRepository;
    private final ClassTypeMapper mapper;

    public ClassTypeRepositoryImpl(ClassTypeJpaRepository jpaRepository,
                                   ClassTypeMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ClassType save(ClassType type) {
        ClassTypeEntity entity = mapper.toEntity(type);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<ClassType> findByName(String name) {
        return jpaRepository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public List<ClassType> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}