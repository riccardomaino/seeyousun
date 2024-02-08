package com.taass.seeyousun.eventservice.services;

import com.taass.seeyousun.eventservice.dto.EventDTO;
import com.taass.seeyousun.eventservice.exception.EventNotFoundException;
import com.taass.seeyousun.eventservice.mappers.impl.EventMapper;
import com.taass.seeyousun.eventservice.model.Event;
import com.taass.seeyousun.eventservice.repository.EventRepository;
import org.springframework.stereotype.Service;

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
        List<Event> eventList = eventRepository.findByResortId(resortId);
        return  eventList.stream()
                .map(eventMapper::mapFrom)
                .toList();
    }

    public void subscribeToEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(()-> new EventNotFoundException(String.format("Event not found with id: '%d'", eventId)));
        event.reserveEvent(userId);
        eventRepository.save(event);
    }

    public List<EventDTO> getEventForUser(Long userId) {
        return eventRepository.findByUSerId(userId)
                .stream()
                .map(eventMapper::mapFrom)
                .toList();
    }
}
