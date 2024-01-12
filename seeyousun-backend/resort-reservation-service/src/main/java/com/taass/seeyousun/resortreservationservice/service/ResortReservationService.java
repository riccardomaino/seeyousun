package com.taass.seeyousun.resortreservationservice.service;

import com.taass.seeyousun.resortreservationservice.DTO.SingleReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.exception.NoSuchResortReservationException;
import com.taass.seeyousun.resortreservationservice.mappers.impl.SingleReservationRequestDTOmapper;
import com.taass.seeyousun.resortreservationservice.model.Reservation;
import com.taass.seeyousun.resortreservationservice.repositories.ReservationRepository;
import com.taass.seeyousun.resortreservationservice.repositories.ResortReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResortReservationService {
    private final SingleReservationRequestDTOmapper singleReservationRequestDTOmapper;
    private final ResortReservationRepository resortReservationRepository;
    private final ReservationRepository reservationRepository;

    public ResortReservationService(SingleReservationRequestDTOmapper singleReservationRequestDTOmapper, ResortReservationRepository resortReservationRepository, ReservationRepository reservationRepository) {
        this.singleReservationRequestDTOmapper = singleReservationRequestDTOmapper;
        this.resortReservationRepository = resortReservationRepository;
        this.reservationRepository = reservationRepository;
    }


    public ResponseEntity<Reservation> saveReservation(SingleReservationRequestDTO requestDTO) throws NoSuchResortReservationException {
        Reservation newReservation = singleReservationRequestDTOmapper.mapTo(requestDTO);

        newReservation.setResortReservation(resortReservationRepository.findById(requestDTO.getResortReservation())
                .orElseThrow(NoSuchResortReservationException::new));
        Reservation reservation = reservationRepository.save(newReservation);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }
    /*
    public ResponseEntity<List<Reservation>> saveReservation(MultipleReservationRequestDTO requestDTO){
        List<LocalDate> dateList =  requestDTO.getIntialDate()
                .datesUntil(requestDTO.getFinalDate())
                .toList();
        List<Reservation> reservationList = new ArrayList<>();
        for(LocalDate actualDate : dateList){
            Reservation actualReservation = new Reservation();

        }
    }*/

}
