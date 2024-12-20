package com.taass.seeyousun.resortservice.dto;

import com.taass.seeyousun.resortservice.model.Service;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResortFullDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private BigDecimal rating;
    private List<String> phoneNumbers;
    private List<String> timeTables;
    private String photoCover;
    private List<String> photos;
    private Set<Service> services;
    private List<ReviewDTO> reviews;
    private List<EventDTO> events;
}
