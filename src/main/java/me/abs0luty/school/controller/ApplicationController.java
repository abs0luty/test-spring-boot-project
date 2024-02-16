package me.abs0luty.school.controller;

import me.abs0luty.school.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public abstract class ApplicationController {

    protected ResponseEntity<ApiResponse> createResponse(String message, HttpStatus status, Object payload) {
        return ResponseEntity.status(status)
                .body(ApiResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .message(message)
                        .status(status)
                        .statusCode(status.value())
                        .payload(payload)
                        .build());
    }

    protected ResponseEntity<ApiResponse> createResponse(String message, HttpStatus status) {
        return createResponse(message, status, null);
    }

    protected ResponseEntity<ApiResponse> createOkResponse(String message) {
        return createResponse(message, HttpStatus.OK);
    }

    protected ResponseEntity<ApiResponse> createOkResponse(String message, Object payload) {
        return createResponse(message, HttpStatus.OK, payload);
    }
}
