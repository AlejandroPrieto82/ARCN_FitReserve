package com.fitreserve.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "class_types")
public class ClassTypeEntity {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;

    public ClassTypeEntity() {}

    public ClassTypeEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}