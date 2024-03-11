package com.taass.seeyousun.eventservice.services;

import com.taass.seeyousun.eventservice.dto.EventDTO;
import com.taass.seeyousun.eventservice.exception.EventAlreadyReservedException;
import com.taass.seeyousun.eventservice.exception.EventFullReservedException;
import com.taass.seeyousun.eventservice.exception.EventNotFoundException;
import com.taass.seeyousun.eventservice.exception.EventNotReservedException;
import com.taass.seeyousun.eventservice.mappers.impl.EventMapper;
import com.taass.seeyousun.eventservice.model.Event;
import com.taass.seeyousun.eventservice.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    private final EventRepository  eventRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDTO> getEventForResort(Long resortId) {
        List<Event> eventList = eventRepository.findByResortIdAndInitialDateTimeAfter(resortId, LocalDateTime.now());
        return  eventList.stream()
                .map(eventMapper::mapFrom)
                .toList();
    }

    public void subscribeToEvent(Long eventId, String userUid) throws EventNotFoundException, EventFullReservedException, EventAlreadyReservedException {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(()-> new EventNotFoundException(String.format("Nessun evento trovato con id: '%d'", eventId)));
        event.reserveEvent(userUid);
        eventRepository.save(event);
    }

    public List<EventDTO> getEventForUser(String userUid) {
        return eventRepository.findByUserId(userUid)
                .stream()
                .map(eventMapper::mapFrom)
                .toList();
    }

    public void unsubscribeToEvent(Long eventId, String userUid) throws EventNotFoundException, EventNotReservedException {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(()-> new EventNotFoundException(String.format("Nessun evento trovato con id: '%d'", eventId)));
        event.unreserveEvent(userUid);
        eventRepository.save(event);
    }
}
