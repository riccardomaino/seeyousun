package com.taass.seeyousun.resortreservationservice.controllers;

import com.taass.seeyousun.resortreservationservice.dto.ApiResponseDTO;
import com.taass.seeyousun.resortreservationservice.dto.ReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.dto.ReservationStateDTO;
import com.taass.seeyousun.resortreservationservice.service.ResortReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
                .message("Success in retrieving pricing list of resort")
                .data(reservationDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponseDTO<Object>> createReservation(@RequestBody ReservationRequestDTO requestDTO){
        resortReservationService.createReservation(requestDTO);
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .statusCode(201)
                .message("Reservation created successfully")
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
