package com.tass.seeyousun.resortservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Temporal(TemporalType.DATE)
    private LocalDate initialDate;

    @Temporal(TemporalType.DATE)
    private LocalDate finalDate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "resort_id")
    @JsonBackReference
    private Resort resort;
}
