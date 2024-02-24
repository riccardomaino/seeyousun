package com.taass.seeyousun.reviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDTO<T> {
    private Integer statusCode;
    private Boolean success;
    private String message;
    private Date timestamp;
    private T data;
}
