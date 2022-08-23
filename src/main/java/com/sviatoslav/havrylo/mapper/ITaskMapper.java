package com.sviatoslav.havrylo.mapper;

import com.sviatoslav.havrylo.dto.TaskDto;
import com.sviatoslav.havrylo.entity.Task;

public interface ITaskMapper {
    TaskDto toDto(Task task);
    Task toEntity(TaskDto taskDto);
    void populateEntity(TaskDto taskDto, Task task);
}
