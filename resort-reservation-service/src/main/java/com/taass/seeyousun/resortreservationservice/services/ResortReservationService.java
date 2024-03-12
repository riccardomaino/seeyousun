package com.taass.seeyousun.resortreservationservice.services;

import com.taass.seeyousun.resortreservationservice.client.ResortClient;
import com.taass.seeyousun.resortreservationservice.dto.*;
import com.taass.seeyousun.resortreservationservice.exceptions.*;
import com.taass.seeyousun.resortreservationservice.model.DailyReservation;
import com.taass.seeyousun.resortreservationservice.model.Reservation;
import com.taass.seeyousun.resortreservationservice.repositories.DailyReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ResortReservationService {
    private final DailyReservationRepository dailyReservationRepository;
    private final ResortClient resortClient;

    public ResortReservationService(
            DailyReservationRepository dailyReservationRepository,
            ResortClient resortClient
    ) {
        this.dailyReservationRepository = dailyReservationRepository;
        this.resortClient = resortClient;
    }

    private void checkReservationRequestValidity(ReservationRequestDTO requestDTO){
        // Effettuiamo chiamata REST al resort-service sincrona per prelevare le dimensioni della mappa del resort (linee x colonne)
        DimensionDTO dimensionDTO = Objects.requireNonNull(resortClient.getResortDimension(requestDTO.getResortId())
                .getBody()).getData();

        // Effettuiamo un controllo sul rispetto dei limiti della mappa del resort
        if(dimensionDTO.getTotalUmbrellaLine() <= requestDTO.getReservedUmbrellaLine() ||
                dimensionDTO.getTotalUmbrellaColumn() <= requestDTO.getReservedUmbrellaColumn() ||
                requestDTO.getReservedUmbrellaLine()  < 0 ||
                requestDTO.getReservedUmbrellaColumn() < 0
        ) {
            throw new UmbrellaOutOfBoundException(
                    String.format("L'ombrellone è fuori dai limiti della mappa del resort, posizione [%d, %d] inesistente",
                            requestDTO.getReservedUmbrellaLine(),
                            requestDTO.getReservedUmbrellaColumn())
            );
        }

        // Controlla che il numero di lettini sia compreso tra 1 e 3
        if(requestDTO.getNumberOfSunbeds()<1)
            throw new SunbedNotValidException("I lettini devono essere almeno 1");
        if(requestDTO.getNumberOfSunbeds() > 3)
            throw new SunbedNotValidException("I lettini devono essere al massimo 3");
    }

    @Transactional
    public void createReservation(ReservationRequestDTO requestDTO){
        // Esecuzione dei controlli di validità sulla richiesta di prenotazione
        checkReservationRequestValidity(requestDTO);
        // cerca il daily reservation se esiste, se no lo crea
        DailyReservation dailyReservation;
        Optional<DailyReservation> dailyReservationOpt =
                dailyReservationRepository.findDistinctByDateAndResortId(requestDTO.getInitialDate(), requestDTO.getResortId());
        dailyReservation = dailyReservationOpt
                .orElseGet(() -> DailyReservation.builder()
                                            .resortId(requestDTO.getResortId())
                                            .date(requestDTO.getInitialDate())
                                            .reservations(new ArrayList<>())
                                            .build());
        //crea la reservation
        Reservation reservation = Reservation.builder()
                .numberOfSunbeds(requestDTO.getNumberOfSunbeds())
                .reservedUmbrellaLine(requestDTO.getReservedUmbrellaLine())
                .reservedUmbrellaColumn(requestDTO.getReservedUmbrellaColumn())
                .persistenceTypeEnum(requestDTO.getPersistenceTypeEnum())
                .userUid(requestDTO.getUserUid())
                .build();
        dailyReservation.addReservation(reservation);
        dailyReservationRepository.save(dailyReservation);
        }

    public List<UmbrellaDTO> getReservedUmbrellaInformation(Long resortId, LocalDate date){
        return dailyReservationRepository.findReservedPlaceOfResortInDate(resortId, date);
    }

    public List<ReservationFullDTO> getReservationForUser(String userUid) {
        List<ReservationFullDTO> reservationList = dailyReservationRepository.findByUser(userUid);
        for(ReservationFullDTO reservation : reservationList){
            String resortName = Objects.requireNonNull(
                    resortClient.getResortName(reservation.getResortId())
                    .getBody()).getData();
            reservation.setResortName(resortName);
        }
        return reservationList;
    }
}
