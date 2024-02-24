package com.taass.seeyousun.resortreservationservice.controllers;

import com.taass.seeyousun.resortreservationservice.dto.*;
import com.taass.seeyousun.resortreservationservice.services.ResortReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/resort-reservations")
@CrossOrigin(origins = "http://localhost:4200")
public class ResortReservationController {
    private final ResortReservationService resortReservationService;

    public ResortReservationController(ResortReservationService resortReservationService) {
        this.resortReservationService = resortReservationService;
    }

    @GetMapping("/umbrella/{resortId}")
    public ResponseEntity<ApiResponseDTO<List<UmbrellaDTO>>> getReservationInformation(
            @PathVariable Long resortId,
            @RequestParam String date) {
        List<UmbrellaDTO> umbrellaDTO = resortReservationService.getReservedUmbrellaInformation(resortId, LocalDate.parse(date));
        ApiResponseDTO<List<UmbrellaDTO>> response = ApiResponseDTO.<List<UmbrellaDTO>>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento delle informazioni degli ombrelloni prenotati avvenuto correttamente")
                .timestamp(new Date())
                .data(umbrellaDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "book")
    public ResponseEntity<ApiResponseDTO<Object>> createReservation(
            @RequestBody ReservationRequestDTO requestDTO,
            @RequestHeader(name = "X-User-UID") String userUid
    ){
        requestDTO.setUserUid(userUid);
        resortReservationService.createReservation(requestDTO);
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .statusCode(201)
                .success(true)
                .message("Successo, creazione della prenotazione avvenuta correttamente")
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("reservation-for-user")
    public ResponseEntity<ApiResponseDTO<List<ReservationFullDTO>>> getReservationsForUser(@RequestHeader(name = "X-User-UID") String userUid){
        List<ReservationFullDTO> reservation = resortReservationService.getReservationForUser(userUid);
        ApiResponseDTO<List<ReservationFullDTO>> response = ApiResponseDTO.<List<ReservationFullDTO>>builder()
                .statusCode(201)
                .success(true)
                .message("Successo, ottenimento delle prenotazioni dell'utente avvenuto correttamente")
                .timestamp(new Date())
                .data(reservation)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
