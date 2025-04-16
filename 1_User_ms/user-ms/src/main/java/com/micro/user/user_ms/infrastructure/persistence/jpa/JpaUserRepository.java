package com.micro.user.user_ms.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.user.user_ms.infrastructure.persistence.entity.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
