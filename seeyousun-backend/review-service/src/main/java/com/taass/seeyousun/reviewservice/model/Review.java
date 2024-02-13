package com.taass.seeyousun.reviewservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String userUid;

    private String title;

    @Length(max=2000)
    private String bodyReview;

    private Integer rating;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    private Long resortId;

}

