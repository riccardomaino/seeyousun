package com.taass.seeyousun.resortreservationservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    @ElementCollection
    @CollectionTable(name = "umbrella_line_price", joinColumns = @JoinColumn(name = "price_period_id"))
    @OrderColumn
    private List<Integer> umbrellaPrice;

    private Integer sunbedPrice;

    @Temporal(TemporalType.DATE)
    private Date periodIntitalDate;

    @Temporal(TemporalType.DATE)
    private Date periodFinalDate;

    @ManyToOne
    @JoinColumn(name = "resort_reservation_id")
    private ResortReservation resortReservation;

    public boolean isInPeriod(Date date) {
        return periodIntitalDate.before(date)  && periodFinalDate.after(date);
    }
}
