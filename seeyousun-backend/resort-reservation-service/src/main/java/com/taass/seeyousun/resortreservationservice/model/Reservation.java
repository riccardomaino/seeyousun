package com.taass.seeyousun.resortreservationservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.taass.seeyousun.resortreservationservice.enums.PersistenceTypeEnum;
import jakarta.persistence.*;
import lombok.*;


/*
* Rappresenta una prenotazione in un singolo giorno. Può essere di mezza giornata al mattino al pomeriggio oppure giornata intera.
* */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numberOfSunbeds;

    private Integer reservedUmbrellaLine;

    private Integer reservedUmbrellaColumn;

    @ManyToOne
    @JoinColumn(name = "daily_reservation_id")
    @JsonBackReference
    @ToString.Exclude
    private DailyReservation dailyReservation;

    @Enumerated(EnumType.STRING)
    private PersistenceTypeEnum persistenceTypeEnum;

    private String userUid;

    public boolean isOverlapped(Reservation newReservation) {
        return isSamePlace(newReservation) && isSameDayPeriod(newReservation);
    }

    private boolean isSameDayPeriod(Reservation newReservation) {
        return (this.persistenceTypeEnum == PersistenceTypeEnum.FULL_DAY ||
                newReservation.persistenceTypeEnum == PersistenceTypeEnum.FULL_DAY ||
                this.persistenceTypeEnum == newReservation.persistenceTypeEnum);
    }

    public boolean isSamePlace(Reservation newReservation){
        return this.reservedUmbrellaLine.equals(newReservation.reservedUmbrellaLine) &&
                this.reservedUmbrellaColumn.equals(newReservation.reservedUmbrellaColumn);
    }
}
