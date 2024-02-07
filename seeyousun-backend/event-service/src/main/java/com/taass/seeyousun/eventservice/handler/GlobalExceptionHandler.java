package com.taass.seeyousun.eventservice.handler;

import com.taass.seeyousun.eventservice.dto.ApiErrorDTO;
import com.taass.seeyousun.eventservice.exception.EventFullReservedException;
import com.taass.seeyousun.eventservice.exception.EventNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleEventNotFoundException(EventNotFoundException e){
        ApiErrorDTO errorDto = ApiErrorDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EventFullReservedException.class)
    public ResponseEntity<ApiErrorDTO> handleEventFullReservedException(EventFullReservedException e){
        ApiErrorDTO errorDto = ApiErrorDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
