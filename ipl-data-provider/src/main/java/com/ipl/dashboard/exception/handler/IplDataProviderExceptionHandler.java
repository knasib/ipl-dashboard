package com.ipl.dashboard.exception.handler;

import com.ipl.dashboard.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class IplDataProviderExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handle(ResourceNotFoundException e) {
        log.error("Resource Not Found Exception!", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("<b>Resource not found!");
    }
}
