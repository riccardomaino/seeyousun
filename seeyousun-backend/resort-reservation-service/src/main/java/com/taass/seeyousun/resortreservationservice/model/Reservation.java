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

    @ManyToOne
    @JoinColumn(name = "resort_reservation_id")
    @JsonBackReference
    private ResortReservation resortReservation;

    private PersistenceType persistencyType;

}
