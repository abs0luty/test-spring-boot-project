package me.abs0luty.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse {

    private LocalDateTime timestamp;

    private int statusCode;
    private String message;

    private Object payload;

    @JsonIgnore
    private HttpStatus status;
}
