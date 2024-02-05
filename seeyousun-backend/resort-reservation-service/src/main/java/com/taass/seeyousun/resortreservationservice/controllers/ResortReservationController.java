package com.taass.seeyousun.resortreservationservice.controllers;

import com.taass.seeyousun.resortreservationservice.dto.ApiResponseDTO;
import com.taass.seeyousun.resortreservationservice.dto.ReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.dto.ReservationStateDTO;
import com.taass.seeyousun.resortreservationservice.exceptions.*;
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

    /*ottenere le informazioni necessarie per prenotare in un resort
    * posti occupati, grandezza della matrice di ombrelloni, costo delle rige degli ombrelloni, costo dei lettini
    * */
    @GetMapping("/information/{resortId}/{date}")
    public ResponseEntity<ApiResponseDTO<ReservationStateDTO>> getReservationInformation(@PathVariable Long resortId, @PathVariable String date) {
        ApiResponseDTO<ReservationStateDTO> response = null;
        try {
            ReservationStateDTO reservationDTO = resortReservationService.getReservationInformation(resortId, LocalDate.parse(date));
            response = ApiResponseDTO.<ReservationStateDTO>builder()
                    .statusCode(200)
                    .message("Success in retrieving pricing list of resort")
                    .data(reservationDTO)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResortNotFoundException | PriceNotSettedException e) {
            response = ApiResponseDTO.<ReservationStateDTO>builder()
                    .statusCode(400)
                    .message("Failed in retrieving pricing list of resort")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/reservation")
    public ResponseEntity<?> saveReservation (@RequestBody ReservationRequestDTO requestDTO){
        try {
            return new ResponseEntity<>(resortReservationService.saveReservation(requestDTO), HttpStatus.CREATED) ;
        } catch (ResortNotFoundException | NoSuchResortReservationException | UmbrellaAlreadyReservedException |
                 UmbrellaOutOfBound e) {
            return new ResponseEntity<>(e.getMessage() + e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
        }
    }
}
