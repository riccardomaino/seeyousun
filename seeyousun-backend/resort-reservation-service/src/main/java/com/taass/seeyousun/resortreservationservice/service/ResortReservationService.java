package com.taass.seeyousun.resortreservationservice.service;

import com.taass.seeyousun.resortreservationservice.client.ResortClient;
import com.taass.seeyousun.resortreservationservice.dto.*;
import com.taass.seeyousun.resortreservationservice.exceptions.*;
import com.taass.seeyousun.resortreservationservice.mappers.impl.ReservationFullDTOmapper;
import com.taass.seeyousun.resortreservationservice.mappers.impl.ReservationRequestDTOmapper;
import com.taass.seeyousun.resortreservationservice.model.DailyReservation;
import com.taass.seeyousun.resortreservationservice.model.Reservation;
import com.taass.seeyousun.resortreservationservice.repositories.DailyReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ResortReservationService {
    private final DailyReservationRepository dailyReservationRepository;
    private final ReservationRequestDTOmapper reservationRequestDTOmapper;
    private final ResortClient resortClient;

    private final ReservationFullDTOmapper reservationFullDTOmapper;

    public ResortReservationService(
            DailyReservationRepository dailyReservationRepository,
            ReservationRequestDTOmapper reservationRequestDTOmapper,
            ResortClient resortClient, ReservationFullDTOmapper reservationFullDTOmapper) {
        this.dailyReservationRepository = dailyReservationRepository;
        this.reservationRequestDTOmapper = reservationRequestDTOmapper;
        this.resortClient = resortClient;
        this.reservationFullDTOmapper = reservationFullDTOmapper;
    }

    public void createReservation(ReservationRequestDTO requestDTO)
            throws ResortNotFoundException, DailyReservationNotFoundException,
                UmbrellaAlreadyReservedException, UmbrellaOutOfBoundException {
        // Effettuiamo chiamata REST al resort-service sincrona per prelevare le dimensioni della mappa del resort (linee x colonne)
        ResponseEntity<ApiResponseDTO<DimensionDTO>> response = resortClient.getResortDimension(requestDTO.getResortId());
        if(response.getStatusCode() != HttpStatus.OK)
            throw new ServiceNotReachableException("Resort service is not reachable");
        DimensionDTO dimensionDTO = Objects.requireNonNull(response.getBody()).getData();

        // Effettuiamo un controllo sul rispetto dei limiti della mappa del resort
        if(dimensionDTO.getTotalUmbrellaLine() <= requestDTO.getReservedUmbrellaLine() ||
                dimensionDTO.getTotalUmbrellaColumn() <= requestDTO.getReservedUmbrellaColumn() ||
                     requestDTO.getReservedUmbrellaLine()  < 0 ||
                        requestDTO.getReservedUmbrellaColumn() < 0
        ) {
            throw new UmbrellaOutOfBoundException(
                    String.format("This umbrella is out of bound: [%d, %d]", requestDTO.getReservedUmbrellaLine(), requestDTO.getReservedUmbrellaColumn()));
        }

        // Ottiene la lista di tutti i dailyReservation in cui aggiungere la nuova prenotazione
        List<DailyReservation> dailyReservationList = dailyReservationRepository.findByResortId(requestDTO.getResortId())
                .stream()
                .filter(rr -> rr.isThisInPeriod(requestDTO.getInitialDate(),requestDTO.getFinalDate()))
                .toList();

        for(DailyReservation dailyReservation : dailyReservationList){
            Reservation reservation = reservationRequestDTOmapper.mapTo(requestDTO);
            dailyReservation.addReservation(reservation);
            dailyReservationRepository.save(dailyReservation);
        }
    }

    public ReservationStateDTO getReservationInformation(long resortId, LocalDate date) throws ResortNotFoundException, PriceNotSettedException {
        // Effettuiamo chiamata REST al resort-service sincrona per prelevare le dimensions (linee x colonne) del resort
        ResponseEntity<ApiResponseDTO<DimensionDTO>> responseDimension = resortClient.getResortDimension(resortId);
        if(responseDimension.getStatusCode() != HttpStatus.OK)
            throw new ServiceNotReachableException("Resort service is not reachable");
        DimensionDTO dimensionDTO = Objects.requireNonNull(responseDimension.getBody()).getData();

        // Effettuiamo chiamata REST al resort-service sincrona per richiedere il listino prezzi
        ResponseEntity<ApiResponseDTO<PriceListDTO>> responsePriceList = resortClient.getResortPrice(resortId, date.toString());
        if(responsePriceList.getStatusCode() != HttpStatus.OK)
            throw new ServiceNotReachableException("Resort service is not reachable");
        PriceListDTO priceListDTO = Objects.requireNonNull(responsePriceList.getBody()).getData();

        // Otteniamo la lista degli ombrelloni occupati
        List<UmbrellaDTO> reservedUmbrella = dailyReservationRepository
                .findByResortIdAndDate(resortId,date)
                .getFirst()
                .getReservation()
                        .stream()
                        .map(r -> new UmbrellaDTO(r.getReservedUmbrellaLine(), r.getReservedUmbrellaColumn(), r.getPersistenceTypeEnum()))
                        .toList();


        return ReservationStateDTO.builder()
                .dimension(dimensionDTO)
                .priceList(priceListDTO)
                .reservedUmbrella(reservedUmbrella)
                .build();
    }

    public List<ReservationFullDTO> getReservationForUser(Long userId) {
        return dailyReservationRepository.findByUser(userId)
                .stream()
                .map(reservationFullDTOmapper::mapFrom)
                .toList();
    }
}
