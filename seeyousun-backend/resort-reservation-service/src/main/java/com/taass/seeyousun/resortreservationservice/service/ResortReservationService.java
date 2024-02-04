package com.taass.seeyousun.resortreservationservice.service;

import com.taass.seeyousun.resortreservationservice.dto.*;
import com.taass.seeyousun.resortreservationservice.exceptions.*;
import com.taass.seeyousun.resortreservationservice.mappers.impl.MultipleReservationRequestDTOmapper;
import com.taass.seeyousun.resortreservationservice.model.Reservation;
import com.taass.seeyousun.resortreservationservice.model.ResortReservation;
import com.taass.seeyousun.resortreservationservice.repositories.ResortClient;
import com.taass.seeyousun.resortreservationservice.repositories.ResortReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResortReservationService {
    private final ResortReservationRepository resortReservationRepository;
    private final MultipleReservationRequestDTOmapper multipleReservationRequestDTOmapper;
    private final ResortClient resortClient;

    public ResortReservationService(
            ResortReservationRepository resortReservationRepository,
            MultipleReservationRequestDTOmapper multipleReservationRequestDTOmapper,
            ResortClient resortClient) {
        this.resortReservationRepository = resortReservationRepository;
        this.multipleReservationRequestDTOmapper = multipleReservationRequestDTOmapper;
        this.resortClient = resortClient;
    }

    public List<Reservation> saveReservation(MultipleReservationRequestDTO requestDTO) throws ResortNotFoundException, NoSuchResortReservationException, UmbrellaAlreadyReservedException, UmbrellaOutOfBound {
        //controlli
        DimensionDTO dimensionDTO = resortClient.getResortDimension(requestDTO.getResort());
        if(dimensionDTO.getTotalUmbrellaLine() <= requestDTO.getReservedUmbrellaLine() &&
                dimensionDTO.getTotalUmbrellaColumn() <= requestDTO.getReservedUmbrellaColumn()) {
            throw new UmbrellaOutOfBound();
        }

        List<ResortReservation> reservationList = resortReservationRepository.findByResortId(requestDTO.getResort())
                .stream()
                .filter(rr -> rr.isInPeriod(requestDTO.getInitialDate(),requestDTO.getFinalDate()))
                .toList();

        List<Reservation> result = new ArrayList<>();

        for(ResortReservation resortReservation: reservationList){
            Reservation reservation = multipleReservationRequestDTOmapper.mapTo(requestDTO);
            resortReservation.addReservation(reservation);
            //TODO controllare se funziona senza il save: resortReservationRepository.save(resortReservation);
            result.add(reservation);
        }
        return result;
    }

    public ReservationStateDTO getReservationInformation(long resortId, LocalDate date) throws ResortNotFoundException, PriceNotSettedException {
        /*interroga Resort chiedendo dimensioni mappa*/
        DimensionDTO dimension = resortClient.getResortDimension(resortId);
        /*interroga Resort chiedendo listino*/
        PriceDTO priceList = resortClient.getResortPrice(resortId, date);
        /*lista degli ombrelloni occupati*/
        List<UmbrellaDTO> reservedUmbrella = resortReservationRepository
                .findByResortIdAndDate(resortId,date)
                .getFirst()
                .getReservation()
                        .stream()
                        .map(r -> new UmbrellaDTO(r.getReservedUmbrellaLine(), r.getReservedUmbrellaColumn(), r.getPersistenceType()))
                        .toList();


        return ReservationStateDTO.builder()
                .dimension(dimension)
                .reservedUmbrella(reservedUmbrella)
                .priceList(priceList)
                .build();
    }

}
