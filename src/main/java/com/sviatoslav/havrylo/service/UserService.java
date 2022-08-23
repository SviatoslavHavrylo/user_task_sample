package com.sviatoslav.havrylo.service;

import com.sviatoslav.havrylo.dto.UserDto;
import com.sviatoslav.havrylo.entity.User;
import com.sviatoslav.havrylo.mapper.IUserMapper;
import com.sviatoslav.havrylo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository repository;
    private final IUserMapper mapper;

    @Override
    public UserDto create(UserDto request) {
        User user = repository.save(mapper.toEntity(request));
        return mapper.toDto(user);
    }

    @Override
    public UserDto update(Long id, UserDto request) {
        User user = findById(id);
        mapper.populateEntity(request, user);
        return mapper.toDto(repository.save(user));
    }

    @Override
    public List<UserDto> getAll() {
        return repository.findAll()
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        return mapper.toDto(findById(id));
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("not found User by id=%s", id)));
    }
}
