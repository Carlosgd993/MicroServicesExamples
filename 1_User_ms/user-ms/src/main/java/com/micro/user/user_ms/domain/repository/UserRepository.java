package com.micro.user.user_ms.domain.repository;

import java.util.List;
import java.util.Optional;

import com.micro.user.user_ms.domain.model.User;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void update(User user);

    void deleteById(Long id);
}
