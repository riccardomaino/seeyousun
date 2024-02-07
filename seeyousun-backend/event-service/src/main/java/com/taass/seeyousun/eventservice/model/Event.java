package com.taass.seeyousun.eventservice.model;

import com.taass.seeyousun.eventservice.exception.EventFullReservedException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "events")
/*
  Un evento può avere un massimo numero di utenti prenotati: per semplicità faremo che gli eventi di gruppo necessitano di una sola prenotazione.
  EX pallavolo ha 1 come massimo numero di utenti prenotabili perché supponiamo che prenoti uno per tutti
  EX Il corso di windsurf ha 10 come massimo numero di utenti prenotabili perché supponiamo che ognuno prenoti per sè
 */
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
    @CollectionTable(name = "event_reservation", joinColumns = @JoinColumn(name = "event_id"))
    private List<Long> userSubscribed;

    private Integer maxUser;

    public Boolean getIsFull(){
        return userSubscribed.size()==maxUser;
    }

    public void reserveEvent(Long userId) {
        if(userSubscribed.size() >= maxUser) throw new EventFullReservedException(name + " è già tutto riservato");
        userSubscribed.add(userId);
    }
}
