package com.sviatoslav.havrylo.service;

import com.sviatoslav.havrylo.dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto create(UserDto request);
    UserDto update(Long id, UserDto request);
    List<UserDto> getAll();
    UserDto getById(Long id);
}
