package com.taass.seeyousun.apigateway.handler;

import com.taass.seeyousun.apigateway.dto.ApiResponseDTO;
import com.taass.seeyousun.apigateway.exceptions.AuthenticationRequiredException;
import com.taass.seeyousun.apigateway.exceptions.FirebaseValidationException;
import com.taass.seeyousun.apigateway.exceptions.InvalidAuthenticationTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FirebaseValidationException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleFirebaseNotWorkingException(FirebaseValidationException e){
        ApiResponseDTO<Object> errorResponseDto = ApiResponseDTO.builder()
                .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                .success(false)
                .message(e.getMessage())
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(AuthenticationRequiredException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleAuthenticationRequiredException(AuthenticationRequiredException e){
        ApiResponseDTO<Object> errorResponseDto = ApiResponseDTO.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .success(false)
                .message(e.getMessage())
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidAuthenticationTokenException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleInvalidAuthenticationTokenException(InvalidAuthenticationTokenException e){
        ApiResponseDTO<Object> errorResponseDto = ApiResponseDTO.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .success(false)
                .message(e.getMessage())
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.UNAUTHORIZED);
    }
}
