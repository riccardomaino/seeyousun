package com.taass.seeyousun.resortreservationservice.dto;


import com.taass.seeyousun.resortreservationservice.enums.PersistenceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UmbrellaDTO {
    private int reservedUmbrellaLine;
    private int reservedUmbrellaColumn;
    private PersistenceTypeEnum persistenceTypeEnum;
}
