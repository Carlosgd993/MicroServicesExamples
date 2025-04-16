package com.micro.user.user_ms.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.micro.user.user_ms.domain.model.User;
import com.micro.user.user_ms.domain.repository.UserRepository;
import com.micro.user.user_ms.infrastructure.persistence.jpa.JpaUserRepository;
import com.micro.user.user_ms.infrastructure.persistence.mapper.UserEntityMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserEntityMapper mapper;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository, UserEntityMapper mapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        var userEntity = mapper.toEntity(user);
        var savedEntity = jpaUserRepository.save(userEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(User user) {
        var userEntity = mapper.toEntity(user);
        jpaUserRepository.save(userEntity);
    }

    @Override
    public void deleteById(Long id) {
        jpaUserRepository.deleteById(id);
    }
}
