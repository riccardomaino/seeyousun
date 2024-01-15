package com.taass.seeyousun.resortreservationservice.DTO;


import com.taass.seeyousun.resortreservationservice.model.PersistenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UmbrellaDTO {
    private int umbrellaLine;
    private int umbrellaColumn;
    private PersistenceType persistenceType;
}
