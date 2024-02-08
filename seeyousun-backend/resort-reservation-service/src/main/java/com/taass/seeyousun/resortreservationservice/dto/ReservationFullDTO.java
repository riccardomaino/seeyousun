package com.taass.seeyousun.resortreservationservice.dto;

import com.taass.seeyousun.resortreservationservice.model.PersistenceTypeEnum;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/*USATO PER RESTITUIRE LE INFORAMZIONI SUUNA PRENOTAZIONE DI UN UTENTE*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationFullDTO {
    private Long resortId;
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    private Integer numberOfSunbeds;
    private PersistenceTypeEnum persistenceTypeEnum;
    private Integer ReservedUmbrellaLine;
    private Integer ReservedUmbrellaColumn;
    private Long userId;
    private Long reservationId;
    private Long DailyReservationId;
}
