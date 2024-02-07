package com.taass.seeyousun.eventservice.controllers;

import com.taass.seeyousun.eventservice.dto.ApiResponseDTO;
import com.taass.seeyousun.eventservice.dto.EventDTO;
import com.taass.seeyousun.eventservice.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{resortId}")
    public ResponseEntity<ApiResponseDTO<List<EventDTO>>> getEventForResort(@PathVariable Long resortId){
        List<EventDTO> eventListDTO = eventService.getEventForResort(resortId);
        ApiResponseDTO<List<EventDTO>> response = ApiResponseDTO.<List<EventDTO>>builder()
                .statusCode(200)
                .message("Success in retrieving event for resort")
                .data(eventListDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/subscription")
    public ResponseEntity<ApiResponseDTO<Object>> subscribeToEvent(@RequestParam Long eventId, Long userId){
        eventService.subscribeToEvent(eventId, userId);
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .statusCode(200)
                .message("Success in subscribing user to the event")
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
