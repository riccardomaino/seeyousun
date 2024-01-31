package com.taass.seeyousun.resortservice.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewMessageDTO {
    private Long resortId;
    private Double averageRating;
}
