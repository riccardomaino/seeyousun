package com.taass.seeyousun.eventservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventSubscriptionRequestDTO {
    private Long eventId;
    private Long userId;
}
