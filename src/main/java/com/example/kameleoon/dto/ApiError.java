package com.example.kameleoon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private LocalDateTime time = LocalDateTime.now();
    private String status;
    private String path;
    private Map<String, String> errors;

    public ApiError(String status, String path, Map<String, String> errors) {
        this.status = status;
        this.path = path;
        this.errors = errors;
    }
}
