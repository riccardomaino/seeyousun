package com.taass.seeyousun.resortreservationservice.controllers;

import com.taass.seeyousun.resortreservationservice.dto.ApiResponseDTO;
import com.taass.seeyousun.resortreservationservice.dto.ReservationFullDTO;
import com.taass.seeyousun.resortreservationservice.dto.ReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.dto.ReservationStateDTO;
import com.taass.seeyousun.resortreservationservice.services.ResortReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/resort-reservations")
public class ResortReservationController {
    private final ResortReservationService resortReservationService;

    public ResortReservationController(ResortReservationService resortReservationService) {
        this.resortReservationService = resortReservationService;
    }

    @GetMapping("/information/{resortId}")
    public ResponseEntity<ApiResponseDTO<ReservationStateDTO>> getReservationInformation(@PathVariable Long resortId, @RequestParam String date) {
        ReservationStateDTO reservationDTO = resortReservationService.getReservationInformation(resortId, LocalDate.parse(date));
        ApiResponseDTO<ReservationStateDTO> response = ApiResponseDTO.<ReservationStateDTO>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento del listino prezzi del resort avvenuto correttamente")
                .timestamp(new Date())
                .data(reservationDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponseDTO<Object>> createReservation(@RequestBody ReservationRequestDTO requestDTO){
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

    @GetMapping("reservation-for-user/{userId}")
    public ResponseEntity<ApiResponseDTO<List<ReservationFullDTO>>> getReservationsForUser(@PathVariable Long userId){
        List<ReservationFullDTO> reservation = resortReservationService.getReservationForUser(userId);
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
