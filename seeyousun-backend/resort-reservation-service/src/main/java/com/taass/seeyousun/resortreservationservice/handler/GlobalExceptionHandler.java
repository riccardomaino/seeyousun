package com.taass.seeyousun.resortreservationservice.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taass.seeyousun.resortreservationservice.dto.ApiResponseDTO;
import com.taass.seeyousun.resortreservationservice.exceptions.*;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResortNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleResortNotFoundException(ResortNotFoundException e){
        ApiResponseDTO<Object> errorResponseDto = ApiResponseDTO.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .success(false)
                .message(e.getMessage())
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DailyReservationNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleDailyReservationNotFoundException(DailyReservationNotFoundException e){
        ApiResponseDTO<Object> errorResponseDto = ApiResponseDTO.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .success(false)
                .message(e.getMessage())
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UmbrellaOutOfBoundException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleUmbrellaOutOfBoundException(UmbrellaOutOfBoundException e){
        ApiResponseDTO<Object> errorResponseDto = ApiResponseDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .success(false)
                .message(e.getMessage())
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UmbrellaAlreadyReservedException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleUmbrellaAlreadyReservedException(UmbrellaAlreadyReservedException e){
        ApiResponseDTO<Object> errorResponseDto = ApiResponseDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .success(false)
                .message(e.getMessage())
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceNotReachableException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleServiceNotReachableException(ServiceNotReachableException e){
        ApiResponseDTO<Object> errorResponseDto = ApiResponseDTO.builder()
                .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                .success(false)
                .message(e.getMessage())
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleFeignException(FeignException e) {
        ObjectMapper objectMapper = new ObjectMapper();
        String strErrorDTO = e.contentUTF8();
        ApiResponseDTO<Object> errorResponseDto;
        try {
            TypeReference<ApiResponseDTO<Object>> typeRef = new TypeReference<>() {};
            errorResponseDto = objectMapper.readValue(strErrorDTO, typeRef);
        } catch (JsonProcessingException ex) {
            errorResponseDto = ApiResponseDTO.builder()
                    .statusCode(e.status())
                    .success(false)
                    .message(e.getMessage())
                    .timestamp(new Date())
                    .data(null)
                    .build();
        }
        return new ResponseEntity<>(errorResponseDto, HttpStatus.valueOf(e.status()));
    }
}
