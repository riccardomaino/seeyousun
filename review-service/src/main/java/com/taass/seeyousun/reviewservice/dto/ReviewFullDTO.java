package com.taass.seeyousun.reviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewFullDTO {
    private Long id;
    private String username;
    private String userUid;
    private String title;
    private String bodyReview;
    private Integer rating;
    private LocalDate date;
    private Long resortId;
}
