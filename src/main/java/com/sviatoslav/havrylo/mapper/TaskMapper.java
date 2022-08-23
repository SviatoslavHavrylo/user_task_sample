package com.sviatoslav.havrylo.mapper;

import com.sviatoslav.havrylo.dto.TaskDto;
import com.sviatoslav.havrylo.entity.Task;
import com.sviatoslav.havrylo.enums.TaskStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TaskMapper implements ITaskMapper {
    @Override
    public TaskDto toDto(Task task) {
        if (Objects.isNull(task)) {
            return null;
        }

        return TaskDto.builder()
                .id(task.getId())
                .userId(task.getUser().getId())
                .name(task.getName())
                .description(task.getDescription())
                .dateTime(task.getDateTime())
                .status(task.getStatus().getName())
                .build();
    }

    @Override
    public Task toEntity(TaskDto taskDto) {
        if (Objects.isNull(taskDto)) {
            return null;
        }
        TaskStatus status = TaskStatus.valueOfName(taskDto.getStatus());

        return Task.builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .description(taskDto.getDescription())
                .dateTime(taskDto.getDateTime())
                .status(status)
                .build();
    }

    @Override
    public void populateEntity(TaskDto taskDto, Task task) {
        if (Objects.isNull(taskDto) || Objects.isNull(task)) {
            return;
        }
        if (StringUtils.isNotBlank(taskDto.getName())) {
            task.setName(taskDto.getName());
        }
        if (StringUtils.isNotBlank(taskDto.getDescription())) {
            task.setDescription(taskDto.getDescription());
        }
        if (Objects.nonNull(taskDto.getDateTime())) {
            task.setDateTime(taskDto.getDateTime());
        }
        if (StringUtils.isNotBlank(taskDto.getStatus())) {
            task.setStatus(TaskStatus.valueOfName(taskDto.getStatus()));
        }
    }
}
