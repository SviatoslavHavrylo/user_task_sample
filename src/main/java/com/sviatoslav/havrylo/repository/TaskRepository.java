package com.sviatoslav.havrylo.repository;

import com.sviatoslav.havrylo.entity.Task;
import com.sviatoslav.havrylo.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByIdAndUser_Id(Long taskId, Long userId);

    List<Task> findAllByUser_Id(Long userId);

    List<Task> findAllByStatusAndDateTimeBefore(TaskStatus taskStatus, LocalDateTime dateTime);
}
