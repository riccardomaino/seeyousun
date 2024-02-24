package com.taass.seeyousun.resortservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResortDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private BigDecimal rating;
    private String photoCover;
}
