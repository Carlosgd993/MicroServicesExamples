package com.micro.user.user_ms.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.micro.user.user_ms.application.dto.UserDTO;
import com.micro.user.user_ms.domain.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User dto);

    User toDomain(UserDTO user);

}
