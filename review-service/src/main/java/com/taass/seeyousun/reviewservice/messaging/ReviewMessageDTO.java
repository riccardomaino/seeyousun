package com.taass.seeyousun.reviewservice.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewMessageDTO {
    private Long resortId;
    private BigDecimal averageRating;
}
