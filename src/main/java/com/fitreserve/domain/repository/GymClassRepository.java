package com.fitreserve.domain.repository;

import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.valueobject.ClassId;

import java.util.List;
import java.util.Optional;

public interface GymClassRepository {

    GymClass save(GymClass gymClass);

    Optional<GymClass> findById(ClassId classId);

    List<GymClass> findAll();

    void deleteById(ClassId classId);
}