package com.tass.seeyousun.resortservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
    THIS class is used to present the resorts in brief
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResortPresentationDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private Integer rating;
    private String photoCover;
}
