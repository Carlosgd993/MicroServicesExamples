package com.micro.user.user_ms.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.micro.user.user_ms.domain.model.User;
import com.micro.user.user_ms.infrastructure.persistence.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity entity);
}