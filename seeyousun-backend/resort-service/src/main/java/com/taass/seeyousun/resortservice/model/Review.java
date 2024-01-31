package com.taass.seeyousun.resortservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "description")
    @Length(max=2000)
    private String description;

    @Column(name = "rating")
    private Integer rating;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @JoinColumn(name = "resort_id")
    private Long resortId;


}

