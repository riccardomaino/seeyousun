package com.taass.seeyousun.resortreservationservice.controllers;

import com.taass.seeyousun.resortreservationservice.DTO.PriceDTO;
import com.taass.seeyousun.resortreservationservice.exception.PriceNotSettedException;
import com.taass.seeyousun.resortreservationservice.repositories.ReservationRepository;
import com.taass.seeyousun.resortreservationservice.repositories.ResortReservationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/resorts-reservation/v1")
public class ResortReservationController {
    private final ResortReservationRepository resortReservationRepository;
    private final ReservationRepository reservationRepository;

    public ResortReservationController(ResortReservationRepository resortReservationRepository, ReservationRepository reservationRepository) {
        this.resortReservationRepository = resortReservationRepository;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/map-dimension/{resort}/{date}")
    public PriceDTO getMapDimension(@PathVariable Long resort, @PathVariable String date){
        try {
            return resortReservationRepository.findByResortAndDate(resort, Date.valueOf(date))
                    .getFirst().getPrice();
        }catch (PriceNotSettedException ex){
            return null;
        }
    }



}