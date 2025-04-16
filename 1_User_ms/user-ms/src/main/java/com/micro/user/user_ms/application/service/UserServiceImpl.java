package com.micro.user.user_ms.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.micro.user.user_ms.domain.model.User;
import com.micro.user.user_ms.domain.repository.UserRepository;
import com.micro.user.user_ms.domain.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User userDTO) {
        // TODO validateUser(user);
        // TODO mappear UserDTO a User
        User user = new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getEmail());

        var createdUser = userRepository.save(user);

        // TODO mappear createdUser a UserDTO
        return new User(
                createdUser.getId(),
                createdUser.getName(),
                createdUser.getEmail());
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new User(
                        user.getId(),
                        user.getName(),
                        user.getEmail()));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        // TODO validateUser(user);
        userRepository.update(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
