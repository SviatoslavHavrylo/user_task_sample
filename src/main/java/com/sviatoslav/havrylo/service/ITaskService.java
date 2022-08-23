package com.sviatoslav.havrylo.service;

import com.sviatoslav.havrylo.dto.TaskDto;

import java.util.List;

public interface ITaskService {
    void delete(Long userId, Long taskId);

    TaskDto create(Long userId, TaskDto request);

    TaskDto update(Long userId, Long taskId, TaskDto request);

    TaskDto getByUserIdAndTaskId(Long userId, Long taskId);

    List<TaskDto> getAllByUserId(Long userId);

    void updateTaskStatus();
}
