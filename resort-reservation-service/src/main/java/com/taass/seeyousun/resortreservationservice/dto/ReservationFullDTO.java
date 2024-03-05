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
    private String resortName;

    public ReservationFullDTO(Long resortId, LocalDate date, Integer numberOfSunbeds, PersistenceTypeEnum persistenceTypeEnum, Integer reservedUmbrellaLine, Integer reservedUmbrellaColumn, String userUid, Long reservationId, Long dailyReservationId) {
        this.resortId = resortId;
        this.date = date;
        this.numberOfSunbeds = numberOfSunbeds;
        this.persistenceTypeEnum = persistenceTypeEnum;
        ReservedUmbrellaLine = reservedUmbrellaLine;
        ReservedUmbrellaColumn = reservedUmbrellaColumn;
        this.userUid = userUid;
        this.reservationId = reservationId;
        DailyReservationId = dailyReservationId;
    }

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
