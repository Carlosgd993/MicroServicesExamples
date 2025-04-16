package com.micro.user.user_ms.infrastructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.user.user_ms.application.dto.UserDTO;
import com.micro.user.user_ms.domain.model.User;
import com.micro.user.user_ms.domain.service.UserService;
import com.micro.user.user_ms.infrastructure.mapper.UserMapper;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<User> getAllUsers() {
        // TODO Mapeo User a Dto
        List<User> searchedUser = userService
                .getAllUsers();
        return !searchedUser.isEmpty() ? searchedUser : null;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        Optional<UserDTO> searchedUser = userService
                .getUserById(id)
                .map(userMapper::toDto);
        return searchedUser.isPresent() ? searchedUser.get() : null;
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userRequest) {
        var user = userMapper.toDomain(userRequest);
        var createdUser = userService.createUser(user);
        return userMapper.toDto(createdUser);
    }

}
