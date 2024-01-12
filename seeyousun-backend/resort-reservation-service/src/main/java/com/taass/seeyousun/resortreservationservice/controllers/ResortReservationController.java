package com.taass.seeyousun.resortreservationservice.controllers;

import com.taass.seeyousun.resortreservationservice.DTO.ReservationDTO;
import com.taass.seeyousun.resortreservationservice.DTO.SingleReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.exception.NoSuchResortReservationException;
import com.taass.seeyousun.resortreservationservice.exception.PriceNotSettedException;
import com.taass.seeyousun.resortreservationservice.model.Reservation;
import com.taass.seeyousun.resortreservationservice.repositories.ResortReservationRepository;
import com.taass.seeyousun.resortreservationservice.service.ResortReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/resorts-reservation/v1")
public class ResortReservationController {
    private final ResortReservationRepository resortReservationRepository;

    private final ResortReservationService resortReservationService;

    public ResortReservationController(ResortReservationRepository resortReservationRepository, ResortReservationService resortReservationService) {
        this.resortReservationRepository = resortReservationRepository;
        this.resortReservationService = resortReservationService;
    }

    /*ottenere le informazioni necessarie per prenotare in un resort
    * posti occupati, grandezza della matrice di ombrelloni, costo delle rige degli ombrelloni, costo dei lettini*/
    @GetMapping("/reservation/{resort}/{date}")
    public ReservationDTO getReservationInformation(@PathVariable Long resort, @PathVariable String date){
        try {
            return resortReservationRepository.findByResortAndDate(resort, Date.valueOf(date))
                    .getFirst().getReservationInformation();
        }catch (PriceNotSettedException ex){
            return null;
        }
    }



    @PostMapping(path = "/reservation",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> saveReservation (@RequestBody SingleReservationRequestDTO requestDTO) {
        try{
            return resortReservationService.saveReservation(requestDTO);
        } catch (NoSuchResortReservationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

        /*
        @PostMapping(path = "/reservation",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveReservation (@RequestBody MultipleReservationRequestDTO requestDTO){
        resortReservationService.saveReservation(requestDTO);
    }*/


}
