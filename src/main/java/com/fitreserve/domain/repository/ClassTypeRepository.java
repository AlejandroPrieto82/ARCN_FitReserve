package com.fitreserve.domain.repository;

import com.fitreserve.domain.model.ClassType;

import java.util.List;
import java.util.Optional;

public interface ClassTypeRepository {

    ClassType save(ClassType type);

    Optional<ClassType> findByName(String name);

    List<ClassType> findAll();
}