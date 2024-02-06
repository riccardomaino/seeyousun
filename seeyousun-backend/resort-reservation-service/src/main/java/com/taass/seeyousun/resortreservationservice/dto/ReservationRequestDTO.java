package com.taass.seeyousun.resortreservationservice.dto;

import com.taass.seeyousun.resortreservationservice.model.PersistenceTypeEnum;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationRequestDTO {
    private Long resortId;
    private Integer reservedUmbrellaLine;
    private Integer reservedUmbrellaColumn;
    private PersistenceTypeEnum persistenceTypeEnum;
    private Integer numberOfSunbeds;
    @Temporal(TemporalType.DATE)
    private LocalDate initialDate;
    @Temporal(TemporalType.DATE)
    private LocalDate finalDate;
}

