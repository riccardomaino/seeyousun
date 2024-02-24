package com.taass.seeyousun.eventservice.controllers;

import com.taass.seeyousun.eventservice.dto.ApiResponseDTO;
import com.taass.seeyousun.eventservice.dto.EventDTO;
import com.taass.seeyousun.eventservice.dto.EventRequestDTO;
import com.taass.seeyousun.eventservice.exception.EventAlreadyReservedException;
import com.taass.seeyousun.eventservice.exception.EventFullReservedException;
import com.taass.seeyousun.eventservice.exception.EventNotFoundException;
import com.taass.seeyousun.eventservice.exception.EventNotReservedException;
import com.taass.seeyousun.eventservice.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/v1/events")
//@CrossOrigin(origins = "http://localhost:4200")
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
                .success(true)
                .message("Successo, ottenimento degli eventi per il resort effettuato correttamente")
                .timestamp(new Date())
                .data(eventListDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/subscription")
    public ResponseEntity<ApiResponseDTO<Object>> subscribeToEvent(
            @RequestBody EventRequestDTO eventRequest,
            @RequestHeader("X-User-UID") String userUid
    ) throws EventNotFoundException, EventFullReservedException, EventAlreadyReservedException {
        eventService.subscribeToEvent(eventRequest.getEventId(), userUid);
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .statusCode(200)
                .success(true)
                .message("Successo, iscrizione dell'utente all'evento effettuata correttamente")
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/unsubscription")
    public ResponseEntity<ApiResponseDTO<Object>> unsubscribeToEvent(
            @RequestBody EventRequestDTO eventRequest,
            @RequestHeader("X-User-UID") String userUid
    ) throws EventNotFoundException, EventNotReservedException {
        eventService.unsubscribeToEvent(eventRequest.getEventId(), userUid);
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .statusCode(200)
                .success(true)
                .message("Successo, disiscrizione dell'utente all'evento effettuata correttamente")
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("events-for-user")
    public ResponseEntity<ApiResponseDTO<List<EventDTO>>> getEventsForUser(@RequestHeader("X-User-UID") String userUid){
        List<EventDTO> eventList = eventService.getEventForUser(userUid);
        ApiResponseDTO<List<EventDTO>> response = ApiResponseDTO.<List<EventDTO>>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento degli eventi dell'utente effettuato correttamente")
                .timestamp(new Date())
                .data(eventList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
