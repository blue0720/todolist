package com.example.todolist.app.infra.entity;

import com.example.todolist.app.domain.model.UserDto;

public class UserMapper {

    public static UserDto toModel(UserEntity entity) {
        return new UserDto(
                entity.getUserId(),
                entity.getUsername(),
                entity.getPassword()
        );
    }

    public static UserEntity toEntity(UserDto model) {
        return new UserEntity(
                model.getUserId(),
                model.getUsername(),
                model.getPassword()
        );
    }
}