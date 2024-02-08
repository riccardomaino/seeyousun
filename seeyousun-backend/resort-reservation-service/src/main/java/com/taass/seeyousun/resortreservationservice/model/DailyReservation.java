package com.taass.seeyousun.resortreservationservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.taass.seeyousun.resortreservationservice.exceptions.UmbrellaAlreadyReservedException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/*
* Contiene tutte le prenotazioni di un dato giorno in un dato resort, e anche la poosizione rispetto alla mappa
* */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "daily_reservations")
public class DailyReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    private Long resortId;

    @OneToMany(mappedBy = "dailyReservation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Reservation> reservations;

    public boolean isThisInPeriod(LocalDate initialDate, LocalDate finalDate){
        return (initialDate.isBefore(this.date) && finalDate.isAfter(this.date)) ||
                (initialDate.isEqual(finalDate) && initialDate.isEqual(this.date));
    }

    public void addReservation(Reservation newReservation) throws UmbrellaAlreadyReservedException {
        boolean isAlreadyReserved = this.reservations.stream()
                .anyMatch(r -> r.isOverlapped(newReservation));

        // Controlla se l'ombrellone è già stato prenotato
        if(isAlreadyReserved)
            throw new UmbrellaAlreadyReservedException(
                    String.format("L'ombrellone in posizione [%d, %d] è già stato prenotato",
                            newReservation.getReservedUmbrellaLine(),
                            newReservation.getReservedUmbrellaColumn())
            );
        // Aggiunti il riferimento alla DailyReservation per la newReservation
        newReservation.setDailyReservation(this);
        // Aggiungi la newReservation alla lista della DailyReservation considerata
        this.reservations.add(newReservation);
    }
}
