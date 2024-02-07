package com.taass.seeyousun.eventservice.mappers.impl;

import com.taass.seeyousun.eventservice.dto.EventDTO;
import com.taass.seeyousun.eventservice.mappers.Mapper;
import com.taass.seeyousun.eventservice.model.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventMapper implements Mapper<EventDTO, Event> {

    private final ModelMapper modelMapper;

    public EventMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Event mapTo(EventDTO eventDTO) {
        return modelMapper.map(eventDTO, Event.class);
    }

    @Override
    public EventDTO mapFrom(Event event) {
        return modelMapper.map(event, EventDTO.class);
    }
}
