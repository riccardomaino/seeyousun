package com.taass.seeyousun.eventservice.model;

import com.taass.seeyousun.eventservice.exception.EventAlreadyReservedException;
import com.taass.seeyousun.eventservice.exception.EventFullReservedException;
import com.taass.seeyousun.eventservice.exception.EventNotReservedException;
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
    private List<String> userSubscribed;

    private Integer maxUser;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT 'FALSE'")
    private Boolean isFull;

    public void reserveEvent(String userUid) {
        // Controlla che l'evento non sia già tutto riservato
        if(this.isFull)
            throw new EventFullReservedException(String.format("L'evento %s è al completo", this.name));
        // Controlla se l'utente è già iscritto all'evento
        if(this.userSubscribed.contains(userUid))
            throw new EventAlreadyReservedException(String.format("L'evento %s è già stato prenotato dall'utente", this.name));
        // Aggiunge l'utente e salva sul Database l'aggiunta
        this.userSubscribed.add(userUid);
        // Controlla se è stato raggiunto il massimo numero di iscritti (se gli iscritto sono infiniti maxUser sarà null): se si è raggiunto si mette "isFull" a true
        if(this.maxUser != null && this.userSubscribed.size() >= this.maxUser)
            this.isFull = true;
    }

    public void unreserveEvent(String userUid) {
        // Controlla se l'utente è effettivamente iscritto all'evento
        if(!this.userSubscribed.contains(userUid))
            throw new EventNotReservedException(String.format("L'evento %s non è stato prenotato dall'utente", this.name));
        // Rimuove l'utente e salva sul Database la rimozione
        this.userSubscribed.remove(userUid);
        // Aggiornamento di "isFull" se l'evento non ha numero infinito di partecipanti (indicato attraverso null) ed era pieno
        if(this.maxUser != null && this.isFull)
            this.isFull = false;
    }
}
