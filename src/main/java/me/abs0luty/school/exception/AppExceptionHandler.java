package me.abs0luty.school.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

public class AppExceptionHandler {

    @ExceptionHandler(AppException.class)
    public void handleAppException(HttpServletResponse response, AppException exception) throws IOException {
        response.sendError(exception.getHttpStatus().value(), exception.getMessage());
    }
}
