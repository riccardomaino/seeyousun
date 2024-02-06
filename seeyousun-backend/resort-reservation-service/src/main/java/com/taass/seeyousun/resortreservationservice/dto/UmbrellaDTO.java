package com.taass.seeyousun.resortreservationservice.dto;


import com.taass.seeyousun.resortreservationservice.model.PersistenceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Usato per ritornare informazioni sulla prenotazione degli ombrelloni
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UmbrellaDTO {
    private int umbrellaLine;
    private int umbrellaColumn;
    private PersistenceTypeEnum persistenceTypeEnum;
}
