package com.taass.seeyousun.resortreservationservice.controllers;

import com.taass.seeyousun.resortreservationservice.dto.MultipleReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.dto.ReservationStateDTO;
import com.taass.seeyousun.resortreservationservice.exceptions.*;
import com.taass.seeyousun.resortreservationservice.service.ResortReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ReservationStateDTO getReservationInformation(@PathVariable Long resortId, @PathVariable String date) {
        try {
            return resortReservationService.getReservationInformation(resortId,LocalDate.parse(date));
        } catch (ResortNotFoundException | PriceNotSettedException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(path = "/reservation",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveReservation (@RequestBody MultipleReservationRequestDTO requestDTO){
        try {
            return new ResponseEntity<>(resortReservationService.saveReservation(requestDTO), HttpStatus.CREATED) ;
        } catch (ResortNotFoundException | NoSuchResortReservationException | UmbrellaAlreadyReservedException |
                 UmbrellaOutOfBound e) {
            return new ResponseEntity<>(e.getMessage() + e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
        }
    }


}
