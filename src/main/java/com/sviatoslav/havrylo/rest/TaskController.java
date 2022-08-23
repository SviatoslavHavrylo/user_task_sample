package com.sviatoslav.havrylo.rest;

import com.sviatoslav.havrylo.dto.TaskDto;
import com.sviatoslav.havrylo.service.ITaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user/{userId}/task")
@RequiredArgsConstructor
public class TaskController {

    private final ITaskService service;

    @Operation(summary = "Creates task")
    @PostMapping
    public ResponseEntity<TaskDto> create(
            @PathVariable Long userId,
            @Valid @RequestBody TaskDto request
    ) {
        return ResponseEntity.ok(service.create(userId, request));
    }

    @Operation(summary = "Update task")
    @PutMapping("{taskId}")
    public ResponseEntity<TaskDto> update(
            @PathVariable Long userId,
            @PathVariable Long taskId,
            @Valid @RequestBody TaskDto request
    ) {
        return ResponseEntity.ok(service.update(userId, taskId, request));
    }

    @Operation(summary = "Delete task")
    @DeleteMapping("{taskId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long userId,
            @PathVariable Long taskId
    ) {
        service.delete(userId, taskId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List all tasks for a user")
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllByUser( @PathVariable Long userId) {
        return ResponseEntity.ok(service.getAllByUserId(userId));
    }

    @Operation(summary = "Get Task Info")
    @GetMapping("{taskId}")
    public ResponseEntity<TaskDto> getById(
            @PathVariable Long userId,
            @PathVariable Long taskId
    ) {
        return ResponseEntity.ok(service.getByUserIdAndTaskId(userId, taskId));
    }
}
