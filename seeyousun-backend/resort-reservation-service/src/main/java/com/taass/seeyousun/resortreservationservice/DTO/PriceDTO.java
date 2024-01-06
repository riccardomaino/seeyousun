package com.taass.seeyousun.resortreservationservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceDTO {

    private int umbrellaLine;

    private int umbrellaColumn;

    private List<Integer> umbrellaPrice;

    private Integer sunbedPrice;


    public PriceDTO(int umbrellaLine, int umbrellaColumn) {
        this.umbrellaLine = umbrellaLine;
        this.umbrellaColumn = umbrellaColumn;
    }
}
