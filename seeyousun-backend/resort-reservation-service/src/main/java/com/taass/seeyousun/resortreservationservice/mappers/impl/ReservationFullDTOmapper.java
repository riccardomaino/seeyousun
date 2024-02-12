package com.taass.seeyousun.resortreservationservice.mappers.impl;

import com.taass.seeyousun.resortreservationservice.dto.ReservationFullDTO;
import com.taass.seeyousun.resortreservationservice.mappers.Mapper;
import com.taass.seeyousun.resortreservationservice.model.DailyReservation;
import com.taass.seeyousun.resortreservationservice.model.Reservation;
import org.springframework.stereotype.Component;


/*SERVE ESCLUSIVAMENTE PER MAPPARE IN RESERVATIONFULLDTO IL RISULTATO DELLA QUERY CHE CERCA TUTTE LE PRENOTAZIONI DI UN UTENTE*/
@Component
public class ReservationFullDTOmapper implements Mapper<ReservationFullDTO, DailyReservation> {
    @Override
    public DailyReservation mapTo(ReservationFullDTO reservationFullDTO) {
        return null;
    }

    @Override
    public ReservationFullDTO mapFrom(DailyReservation dailyReservation) {
        //TODO fare sta cazzata che non funziona perche pijo sempre la prima entry
        Reservation r = dailyReservation.getReservations().getFirst();
        return ReservationFullDTO.builder()
                .resortId(dailyReservation.getResortId())
                .date(dailyReservation.getDate())
                .numberOfSunbeds(r.getNumberOfSunbeds())
                .persistenceTypeEnum(r.getPersistenceTypeEnum())
                .ReservedUmbrellaLine(r.getReservedUmbrellaLine())
                .ReservedUmbrellaColumn(r.getReservedUmbrellaColumn())
                .userUid(r.getUserUid())
                .reservationId(r.getId())
                .DailyReservationId(dailyReservation.getId())
                .build();
    }
}
