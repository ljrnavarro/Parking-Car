package com.parkcar.exception;

import com.parkcar.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDTO> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(
                ResponseDTO.builder()
                        .error(ex.getMessage())
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build()
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ResponseDTO> handleConflict(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ResponseDTO.builder()
                        .error(ex.getMessage())
                        .statusCode(HttpStatus.CONFLICT.value())
                        .build()
        );
    }
}
