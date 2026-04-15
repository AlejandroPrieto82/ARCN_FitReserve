package com.fitreserve.infrastructure.persistence.repository;

import com.fitreserve.domain.model.ClassType;
import com.fitreserve.domain.repository.ClassTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClassTypeRepositoryImpl implements ClassTypeRepository {

    @Override
    public ClassType save(ClassType type) {
        return type;
    }

    @Override
    public Optional<ClassType> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<ClassType> findAll() {
        return List.of();
    }
}