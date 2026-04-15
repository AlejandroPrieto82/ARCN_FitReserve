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

    public UserRepositoryImpl(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        return UserMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return jpaRepository.findById(userId.getValue())
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(UserMapper::toDomain);
    }
}