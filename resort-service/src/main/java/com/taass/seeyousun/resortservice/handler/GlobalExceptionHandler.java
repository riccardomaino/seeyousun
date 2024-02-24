package com.taass.seeyousun.resortservice.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taass.seeyousun.resortservice.dto.ApiResponseDTO;
import com.taass.seeyousun.resortservice.exceptions.PriceNotFoundException;
import com.taass.seeyousun.resortservice.exceptions.ResortNotFoundException;
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

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handlePriceNotFoundException(PriceNotFoundException e){
        ApiResponseDTO<Object> errorResponseDto = ApiResponseDTO.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .success(false)
                .message(e.getMessage())
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
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
