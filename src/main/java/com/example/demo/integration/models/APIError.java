package com.example.demo.integration.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class APIError {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String details;
    private String path;
}
