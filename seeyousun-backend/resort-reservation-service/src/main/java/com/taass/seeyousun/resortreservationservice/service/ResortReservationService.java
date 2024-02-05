package com.taass.seeyousun.resortreservationservice.service;

import com.taass.seeyousun.resortreservationservice.dto.*;
import com.taass.seeyousun.resortreservationservice.exceptions.*;
import com.taass.seeyousun.resortreservationservice.mappers.impl.ReservationRequestDTOmapper;
import com.taass.seeyousun.resortreservationservice.model.Reservation;
import com.taass.seeyousun.resortreservationservice.model.ResortReservation;
import com.taass.seeyousun.resortreservationservice.repositories.ResortClient;
import com.taass.seeyousun.resortreservationservice.repositories.ResortReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ResortReservationService {
    private final ResortReservationRepository resortReservationRepository;
    private final ReservationRequestDTOmapper reservationRequestDTOmapper;
    private final ResortClient resortClient;

    public ResortReservationService(
            ResortReservationRepository resortReservationRepository,
            ReservationRequestDTOmapper reservationRequestDTOmapper,
            ResortClient resortClient) {
        this.resortReservationRepository = resortReservationRepository;
        this.reservationRequestDTOmapper = reservationRequestDTOmapper;
        this.resortClient = resortClient;
    }

    public List<Reservation> saveReservation(ReservationRequestDTO requestDTO) throws ResortNotFoundException, NoSuchResortReservationException, UmbrellaAlreadyReservedException, UmbrellaOutOfBound {
        //controlli
        ResponseEntity<ApiResponseDTO<DimensionDTO>> response = resortClient.getResortDimension(requestDTO.getResort());
        //TODO cambiare eccezione
        if(response.getStatusCode() != HttpStatus.OK)throw new ResortNotFoundException("Client non ha funzionato" + response.getStatusCode());
        DimensionDTO dimensionDTO = Objects.requireNonNull(response.getBody()).getData();

        if(dimensionDTO.getTotalUmbrellaLine() <= requestDTO.getReservedUmbrellaLine() &&
                dimensionDTO.getTotalUmbrellaColumn() <= requestDTO.getReservedUmbrellaColumn()) {
            throw new UmbrellaOutOfBound();
        }

        List<ResortReservation> reservationList = resortReservationRepository.findByResortId(requestDTO.getResort())
                .stream()
                .filter(rr -> rr.isThisInPeriod(requestDTO.getInitialDate(),requestDTO.getFinalDate()))
                .toList();

        List<Reservation> result = new ArrayList<>();

        for(ResortReservation resortReservation: reservationList){
            Reservation reservation = reservationRequestDTOmapper.mapTo(requestDTO);
            resortReservation.addReservation(reservation);
            resortReservationRepository.save(resortReservation);
            result.add(reservation);
        }
        return result;
    }

    public ReservationStateDTO getReservationInformation(long resortId, LocalDate date) throws ResortNotFoundException, PriceNotSettedException {
        /*interroga Resort chiedendo dimensioni mappa*/
        ResponseEntity<ApiResponseDTO<DimensionDTO>> responseDimension = resortClient.getResortDimension(resortId);
        //TODO cambiare eccezione
        if(responseDimension.getStatusCode() != HttpStatus.OK) throw new ResortNotFoundException("Client non ha funzionato" + responseDimension.getStatusCode());
        DimensionDTO dimensionDTO = Objects.requireNonNull(responseDimension.getBody()).getData();

        /*interroga Resort chiedendo listino*/
        ResponseEntity<ApiResponseDTO<PriceDTO>> responsePriceList = resortClient.getResortPrice(resortId, date.toString());
        if(responsePriceList.getStatusCode() != HttpStatus.OK) throw new ResortNotFoundException("Client non ha funzionato" + responsePriceList.getStatusCode());
        PriceDTO priceDTO = Objects.requireNonNull(responsePriceList.getBody()).getData();

        /*lista degli ombrelloni occupati*/
        List<UmbrellaDTO> reservedUmbrella = resortReservationRepository
                .findByResortIdAndDate(resortId,date)
                .getFirst()
                .getReservation()
                        .stream()
                        .map(r -> new UmbrellaDTO(r.getReservedUmbrellaLine(), r.getReservedUmbrellaColumn(), r.getPersistenceType()))
                        .toList();


        return ReservationStateDTO.builder()
                .dimension(dimensionDTO)
                .priceList(priceDTO)
                .reservedUmbrella(reservedUmbrella)
                .build();
    }

}
