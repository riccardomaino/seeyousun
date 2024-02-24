package com.taass.seeyousun.eventservice.handler;

import com.taass.seeyousun.eventservice.dto.ApiResponseDTO;
import com.taass.seeyousun.eventservice.exception.EventAlreadyReservedException;
import com.taass.seeyousun.eventservice.exception.EventFullReservedException;
import com.taass.seeyousun.eventservice.exception.EventNotFoundException;
import com.taass.seeyousun.eventservice.exception.EventNotReservedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleEventNotFoundException(EventNotFoundException e){
        ApiResponseDTO<Object> errorResponseDto = ApiResponseDTO.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .success(false)
                .message(e.getMessage())
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EventFullReservedException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleEventFullReservedException(EventFullReservedException e){
        ApiResponseDTO<Object> errorResponseDto = ApiResponseDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .success(false)
                .message(e.getMessage())
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventAlreadyReservedException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleEventFullReservedException(EventAlreadyReservedException e){
        ApiResponseDTO<Object> errorResponseDto = ApiResponseDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .success(false)
                .message(e.getMessage())
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventNotReservedException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleEventNotReservedException(EventNotReservedException e){
        ApiResponseDTO<Object> errorResponseDto = ApiResponseDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .success(false)
                .message(e.getMessage())
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
