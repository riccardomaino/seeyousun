package com.taass.seeyousun.resortreservationservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private DailyReservation dailyReservation;

    @Enumerated(EnumType.STRING)
    private PersistenceTypeEnum persistenceTypeEnum;

    private String userUid;

    public boolean isOverlapped(Reservation newReservation) {
        return this.reservedUmbrellaLine.equals(newReservation.reservedUmbrellaLine) &&
                this.reservedUmbrellaColumn.equals(newReservation.reservedUmbrellaColumn) &&
                (this.persistenceTypeEnum == PersistenceTypeEnum.FULL_DAY ||
                        newReservation.persistenceTypeEnum == PersistenceTypeEnum.FULL_DAY ||
                        this.persistenceTypeEnum == newReservation.persistenceTypeEnum);
    }
}
