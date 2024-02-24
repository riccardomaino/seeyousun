package com.taass.seeyousun.resortreservationservice.dto;

import com.taass.seeyousun.resortreservationservice.enums.PersistenceTypeEnum;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


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
    private String userUid;
    private Long reservationId;
    private Long DailyReservationId;
}
