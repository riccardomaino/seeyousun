package com.taass.seeyousun.resortreservationservice.controllers;

import com.taass.seeyousun.resortreservationservice.DTO.MultipleReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.DTO.ReservationDTO;
import com.taass.seeyousun.resortreservationservice.DTO.SingleReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.exception.NoSuchResortReservationException;
import com.taass.seeyousun.resortreservationservice.exception.PriceNotSettedException;
import com.taass.seeyousun.resortreservationservice.exception.ResortNotFoundException;
import com.taass.seeyousun.resortreservationservice.exception.UmbrellaAlreadyReservedException;
import com.taass.seeyousun.resortreservationservice.service.ResortReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/resorts-reservation/v1")
public class ResortReservationController {
    private final ResortReservationService resortReservationService;

    public ResortReservationController(ResortReservationService resortReservationService) {
        this.resortReservationService = resortReservationService;
    }

    /*ottenere le informazioni necessarie per prenotare in un resort
    * posti occupati, grandezza della matrice di ombrelloni, costo delle rige degli ombrelloni, costo dei lettini
    * TODO: aggiungere in ReservedUmbrella informazione relativa a occupazione per metà giornata o intera(in front end meta giornata mezzo pallino)
    * */
    @GetMapping("/reservation/{resortId}/{date}")
    public ReservationDTO getReservationInformation(@PathVariable Long resortId, @PathVariable String date) {
        try {
            return resortReservationService.getReservationInfo(resortId,LocalDate.parse(date));
        } catch (ResortNotFoundException | PriceNotSettedException e) {
            throw new RuntimeException(e);
        }
    }



    @PostMapping(path = "/reservation",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveReservation (@RequestBody SingleReservationRequestDTO requestDTO) {
        try {
            return new ResponseEntity<>(resortReservationService.saveReservation(requestDTO), HttpStatus.CREATED);
        } catch (NoSuchResortReservationException | UmbrellaAlreadyReservedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(path = "/multiple-reservation",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveReservation (@RequestBody MultipleReservationRequestDTO requestDTO){
        try {
            return new ResponseEntity<>(resortReservationService.saveReservation(requestDTO), HttpStatus.CREATED) ;
        } catch (ResortNotFoundException | NoSuchResortReservationException | UmbrellaAlreadyReservedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
