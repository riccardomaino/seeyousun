package com.taass.seeyousun.resortreservationservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "resort")
public class Resort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int totalUmbrellaLine;

    private int totalUmbrellaColumn;

    @OneToMany(mappedBy = "resort")
    @JsonManagedReference
    private List<ResortReservation> resortReservationList;

    @OneToMany(mappedBy = "resort")
    @JsonManagedReference
    private List<PricePeriod> pricePeriodList;
}
