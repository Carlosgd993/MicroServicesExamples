package com.micro.user.user_ms.domain.service;

import java.util.List;
import java.util.Optional;

import com.micro.user.user_ms.domain.model.User;

public interface UserService {
    User createUser(User user);

    Optional<User> getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long id);
}