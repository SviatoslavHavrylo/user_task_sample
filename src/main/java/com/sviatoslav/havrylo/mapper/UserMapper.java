package com.sviatoslav.havrylo.mapper;

import com.sviatoslav.havrylo.dto.UserDto;
import com.sviatoslav.havrylo.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper implements IUserMapper {

    @Override
    public UserDto toDto(User user) {
        if (Objects.isNull(user)) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    @Override
    public User toEntity(UserDto userDto) {
        if (Objects.isNull(userDto)) {
            return null;
        }

        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }

    @Override
    public void populateEntity(UserDto userDto, User user) {
        if (Objects.isNull(userDto) || Objects.isNull(user)) {
            return;
        }
        if (StringUtils.isNotBlank(userDto.getUsername())) {
            user.setUsername(userDto.getUsername());
        }
        if (StringUtils.isNotBlank(userDto.getFirstName())) {
            user.setFirstName(userDto.getFirstName());
        }
        if (StringUtils.isNotBlank(userDto.getLastName())) {
            user.setLastName(userDto.getLastName());
        }
    }
}
