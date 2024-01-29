package com.taass.seeyousun.resortreservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorDTO {
    private Integer statusCode;
    private String message;
    private Date timestamp;
}
