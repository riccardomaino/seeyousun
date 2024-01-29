package com.taass.seeyousun.resortreservationservice.dto;

import com.taass.seeyousun.resortreservationservice.model.PersistenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SingleReservationRequestDTO {
    private long resortReservation;
    private int reservedUmbrellaLine;
    private int reservedUmbrellaColumn;

    private PersistenceType persistenceType;

    private int numberOfSunbeds;


}
