package com.taass.seeyousun.resortservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDTO {
    private Long id;
    private String name;
    private LocalDateTime initialDateTime;
    private Integer duration;
    private String description;
    private Boolean isFull;
}
