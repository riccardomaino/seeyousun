package com.taass.seeyousun.resortreservationservice.service;

import com.taass.seeyousun.resortreservationservice.DTO.MultipleReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.DTO.ReservationDTO;
import com.taass.seeyousun.resortreservationservice.DTO.SingleReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.exception.NoSuchResortReservationException;
import com.taass.seeyousun.resortreservationservice.exception.PriceNotSettedException;
import com.taass.seeyousun.resortreservationservice.exception.ResortNotFoundException;
import com.taass.seeyousun.resortreservationservice.exception.UmbrellaAlreadyReservedException;
import com.taass.seeyousun.resortreservationservice.mappers.impl.MultipleReservationRequestDTOmapper;
import com.taass.seeyousun.resortreservationservice.mappers.impl.SingleReservationRequestDTOmapper;
import com.taass.seeyousun.resortreservationservice.model.Reservation;
import com.taass.seeyousun.resortreservationservice.model.Resort;
import com.taass.seeyousun.resortreservationservice.model.ResortReservation;
import com.taass.seeyousun.resortreservationservice.repositories.ResortRepository;
import com.taass.seeyousun.resortreservationservice.repositories.ResortReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResortReservationService {
    private final SingleReservationRequestDTOmapper singleReservationRequestDTOmapper;

    private final ResortReservationRepository resortReservationRepository;
    private final MultipleReservationRequestDTOmapper multipleReservationRequestDTOmapper;
    private final ResortRepository resortRepository;

    public ResortReservationService(SingleReservationRequestDTOmapper singleReservationRequestDTOmapper, ResortReservationRepository resortReservationRepository, MultipleReservationRequestDTOmapper multipleReservationRequestDTOmapper, ResortRepository resortRepository) {
        this.singleReservationRequestDTOmapper = singleReservationRequestDTOmapper;
        this.resortReservationRepository = resortReservationRepository;
        this.multipleReservationRequestDTOmapper = multipleReservationRequestDTOmapper;
        this.resortRepository = resortRepository;
    }

    @Transactional
    public List<Reservation> saveReservation(MultipleReservationRequestDTO requestDTO) throws ResortNotFoundException, NoSuchResortReservationException, UmbrellaAlreadyReservedException {
        Resort resort = resortRepository.findById(requestDTO.getResort())
                .orElseThrow(ResortNotFoundException::new);

        List<ResortReservation> reservationList = resort.getResortReservationList()
                .stream()
                .filter(rr -> rr.isInPeriod(requestDTO.getInitialDate(),requestDTO.getFinalDate()))
                .toList();

        List<Reservation> result = new ArrayList<>();

        for(ResortReservation resortReservation: reservationList){
            Reservation reservation = multipleReservationRequestDTOmapper.mapTo(requestDTO);
            addNewReservation(reservation,resortReservation);
            result.add(reservation);
        }
        return result;
    }


    @Transactional
    public Reservation saveReservation(SingleReservationRequestDTO requestDTO) throws NoSuchResortReservationException, UmbrellaAlreadyReservedException {
        Reservation newReservation = singleReservationRequestDTOmapper.mapTo(requestDTO);
        ResortReservation rr = resortReservationRepository.findById(requestDTO.getResortReservation())
                .orElseThrow(NoSuchResortReservationException::new);
        return addNewReservation(newReservation, rr);
    }

    @Transactional
    public Reservation addNewReservation(Reservation newReservation, ResortReservation resortReservation) throws UmbrellaAlreadyReservedException {
        resortReservation.addReservation(newReservation);
        resortReservationRepository.save(resortReservation);
        return newReservation;
    }

    public ReservationDTO getReservationInfo (long resortId, LocalDate date) throws ResortNotFoundException, PriceNotSettedException {
        Resort resort = resortRepository.findById(resortId)
                .orElseThrow(ResortNotFoundException::new);
        return resortReservationRepository.findByResortAndDate(resort, date)
                    .getFirst().getReservationInformation();
    }

}
