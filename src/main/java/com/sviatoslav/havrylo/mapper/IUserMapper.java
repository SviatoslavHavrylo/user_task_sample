package com.sviatoslav.havrylo.mapper;

import com.sviatoslav.havrylo.dto.UserDto;
import com.sviatoslav.havrylo.entity.User;

public interface IUserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    void populateEntity(UserDto request, User user);
}
