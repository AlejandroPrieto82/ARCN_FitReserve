package com.fitreserve.infrastructure.persistence.repository;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.UserRepository;
import com.fitreserve.domain.valueobject.UserId;
import com.fitreserve.infrastructure.persistence.entity.UserEntity;
import com.fitreserve.infrastructure.persistence.mapper.UserMapper;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;
    private final UserMapper mapper;

    public UserRepositoryImpl(UserJpaRepository jpaRepository, UserMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return jpaRepository.findById(userId.getValue())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(mapper::toDomain);
    }
}