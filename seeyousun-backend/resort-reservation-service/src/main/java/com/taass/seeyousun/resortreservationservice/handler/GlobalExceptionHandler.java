package com.taass.seeyousun.resortreservationservice.handler;

import com.taass.seeyousun.resortreservationservice.dto.ApiErrorDTO;
import com.taass.seeyousun.resortreservationservice.exceptions.DailyReservationNotFoundException;
import com.taass.seeyousun.resortreservationservice.exceptions.ResortNotFoundException;
import com.taass.seeyousun.resortreservationservice.exceptions.UmbrellaAlreadyReservedException;
import com.taass.seeyousun.resortreservationservice.exceptions.UmbrellaOutOfBoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResortNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleResortNotFoundException(ResortNotFoundException e){
        ApiErrorDTO errorDto = ApiErrorDTO.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DailyReservationNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleDailyReservationNotFoundException(DailyReservationNotFoundException e){
        ApiErrorDTO errorDto = ApiErrorDTO.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UmbrellaOutOfBoundException.class)
    public ResponseEntity<ApiErrorDTO> handleUmbrellaOutOfBoundException(UmbrellaOutOfBoundException e){
        ApiErrorDTO errorDto = ApiErrorDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UmbrellaAlreadyReservedException.class)
    public ResponseEntity<ApiErrorDTO> handleUmbrellaAlreadyReservedException(UmbrellaAlreadyReservedException e){
        ApiErrorDTO errorDto = ApiErrorDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
