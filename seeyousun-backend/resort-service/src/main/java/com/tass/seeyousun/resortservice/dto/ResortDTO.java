package com.tass.seeyousun.resortservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResortDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private Integer rating;
    // private FOTO
    private List<String> services;
    private List<ReviewDTO> reviews;
    private List<EventDTO> events;
}
