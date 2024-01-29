package com.taass.seeyousun.resortreservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDTO {

    private int totalUmbrellaLine;

    private int totalUmbrellaColumn;

    //lista ordinata per file dei prezzi degli ombrelloni:
    //[15,10,10] in prima fila si paga 15, in seconda 10 in terza 10
    private List<Integer> umbrellaPrice;

    private Integer sunbedPrice;

    private List<UmbrellaDTO> reservedUmbrella;

    public ReservationDTO(int umbrellaLine, int umbrellaColumn) {
        this.totalUmbrellaLine = umbrellaLine;
        this.totalUmbrellaColumn = umbrellaColumn;
    }
}
