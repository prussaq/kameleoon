package com.example.kameleoon.controller;

import com.example.kameleoon.dto.UserDto;
import com.example.kameleoon.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody UserDto userDto) {
        Long userId = userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("user created with id=" + userId);
    }
}
