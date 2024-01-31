package com.taass.seeyousun.resortservice.handler;

import com.taass.seeyousun.resortservice.dto.ApiErrorDTO;
import com.taass.seeyousun.resortservice.exceptions.ResortNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResortNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleResortNotFoundException(ResortNotFoundException e){
        ApiErrorDTO errorDto = ApiErrorDTO.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timestamp(LocalDate.now())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
