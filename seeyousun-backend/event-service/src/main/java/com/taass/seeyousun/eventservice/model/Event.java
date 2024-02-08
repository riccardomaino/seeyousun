package com.taass.seeyousun.eventservice.model;

import com.taass.seeyousun.eventservice.exception.EventFullReservedException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Questa classe rappresenta un entity "Event". Un Event ha un massimo numero di utenti che si possono prenotare.
 * Per semplicità faremo che gli eventi di gruppo necessitano di una sola prenotazione. Alcuni esempi sono i seguenti:
 *   - es: L'evento "pallavolo" ha 1 come max numero di utenti che si possono prenotare: un'utente prenota per tutti.
 *   - es: L'evento "corso di windsurf" ha 10 come max numero di utenti che si possono prenotare: un'utente prenota per sè.
 */
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
    private LocalDateTime initialDateTime;
    private Integer duration;
    private String description;
    private Long resortId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "events_reservations", joinColumns = @JoinColumn(name = "event_id"))
    private List<Long> userSubscribed;

    private Integer maxUser;
    private Boolean isFull;

    public void reserveEvent(Long userId) {
        // Controlla che l'evento non sia già tutto riservato
        if(isFull)
            throw new EventFullReservedException(String.format("L'evento %s è al completo", this.name));
        // Aggiunge l'utente e salva sul Database l'aggiunta
        this.userSubscribed.add(userId);
        // Controlla se si è stato raggiunto il massimo numero di iscritti: se si mette "isFull" a true
        if(this.userSubscribed.size() >= this.maxUser)
            this.isFull = true;
    }
}
