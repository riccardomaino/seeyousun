package com.taass.seeyousun.reviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorDTO {
    private Integer statusCode;
    private String message;
    private LocalDate timestamp;
}