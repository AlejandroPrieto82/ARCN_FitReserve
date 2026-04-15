package com.fitreserve.domain.repository;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.valueobject.Email;
import com.fitreserve.domain.valueobject.UserId;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UserId id);

    Optional<User> findByEmail(Email email);
}