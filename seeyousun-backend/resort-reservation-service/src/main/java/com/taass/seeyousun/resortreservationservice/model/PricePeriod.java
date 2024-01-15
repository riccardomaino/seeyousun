package com.taass.seeyousun.resortreservationservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


/*Un array di prezzi crescenti in base alla fila, valido per il periodo specificato tra le due date*/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "price_period")
public class PricePeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "umbrella_line_price", joinColumns = @JoinColumn(name = "price_period_id"))
    @OrderColumn
    private List<Integer> umbrellaPrice;

    private Integer sunbedPrice;

    @Temporal(TemporalType.DATE)
    private LocalDate periodIntitalDate;

    @Temporal(TemporalType.DATE)
    private LocalDate periodFinalDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resort_id")
    @JsonBackReference
    private Resort resort;

    public boolean isInPeriod(LocalDate date) {
        return periodIntitalDate.isBefore(date)  && periodFinalDate.isAfter(date);
    }
}
