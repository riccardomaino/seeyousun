package com.taass.seeyousun.resortreservationservice.DTO;


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
}
