package com.taass.seeyousun.resortreservationservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
* Rappresenta una prenotazione in un singolo giorno. può essre di mezza giornata al mattino al pomeriggio oppure giornata intera.
* */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numberOfSunbeds;

    private int reservedUmbrellaLine;

    private int reservedUmbrellaColumn;

    @ManyToOne
    @JoinColumn(name = "resort_reservation_id")
    @JsonBackReference
    private ResortReservation resortReservation;

    private PersistenceType persistenceType;

    public boolean isSamePlaceAndSameDayTime(Reservation newReservation) {
        return this.reservedUmbrellaLine == newReservation.reservedUmbrellaLine &&
                this.reservedUmbrellaColumn == newReservation.reservedUmbrellaColumn &&
                (this.persistenceType == PersistenceType.FULL_DAY ||
                        newReservation.persistenceType == PersistenceType.FULL_DAY ||
                                this.persistenceType == newReservation.persistenceType);
    }
}
