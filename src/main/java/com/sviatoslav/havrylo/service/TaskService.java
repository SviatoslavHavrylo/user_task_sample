package com.sviatoslav.havrylo.service;

import com.sviatoslav.havrylo.dto.TaskDto;
import com.sviatoslav.havrylo.entity.Task;
import com.sviatoslav.havrylo.entity.User;
import com.sviatoslav.havrylo.enums.TaskStatus;
import com.sviatoslav.havrylo.mapper.ITaskMapper;
import com.sviatoslav.havrylo.mapper.IUserMapper;
import com.sviatoslav.havrylo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {

    private final UserService userService;
    private final TaskRepository repository;
    private final ITaskMapper mapper;

    @Override
    public void delete(Long userId, Long taskId) {
        log.info("Request to delete Task: {}", taskId);
        Task task = findByUserIdAndTaskId(userId, taskId);
        repository.delete(task);
    }

    @Override
    public TaskDto create(Long userId, TaskDto request) {
        log.info("Request to create Task");
        User user = userService.findById(userId);
        Task task = mapper.toEntity(request);
        task.setUser(user);
        if (Objects.isNull(task.getStatus())) {
            task.setStatus(TaskStatus.PENDING);
        }
        return mapper.toDto(repository.save(task));
    }

    @Override
    public TaskDto update(Long userId, Long taskId, TaskDto request) {
        log.info("Request to update Task");
        Task task = findByUserIdAndTaskId(userId, taskId);
        mapper.populateEntity(request, task);
        return mapper.toDto(repository.save(task));
    }

    @Override
    public TaskDto getByUserIdAndTaskId(Long userId, Long taskId) {
        return mapper.toDto(findByUserIdAndTaskId(userId, taskId));
    }

    @Override
    public List<TaskDto> getAllByUserId(Long userId) {
        return repository.findAllByUser_Id(userId)
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateTaskStatus() {
        repository.findAllByStatusAndDateTimeBefore(TaskStatus.PENDING, LocalDateTime.now())
                .forEach(this::updateStatusByTime);

    }

    private void updateStatusByTime(Task task) {
        log.info(task.toString());
        task.setStatus(TaskStatus.DONE);
        repository.save(task);
    }

    private Task findByUserIdAndTaskId(Long userId, Long taskId) {
        return repository.findByIdAndUser_Id(taskId, userId)
                .orElseThrow(() -> new RuntimeException(String.format("not found Task by userId=%s and taskId=%s", userId, taskId)));
    }
}
