package com.sviatoslav.havrylo.rest;

import com.sviatoslav.havrylo.dto.UserDto;
import com.sviatoslav.havrylo.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;

    @Operation(summary = "Creates user")
    @PostMapping
    public ResponseEntity<UserDto> create(
            @Valid @RequestBody UserDto request
    ) {
        return ResponseEntity.ok(service.create(request));
    }

    @Operation(summary = "Update user")
    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UserDto request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(summary = "List all users")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Get User info")
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
}
