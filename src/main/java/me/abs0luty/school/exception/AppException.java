package me.abs0luty.school.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class AppException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;
}
