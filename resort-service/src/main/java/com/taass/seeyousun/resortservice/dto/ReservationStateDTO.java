package com.taass.seeyousun.resortservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationStateDTO {
    private DimensionDTO dimension;
    private PriceListDTO priceList;
    private List<UmbrellaDTO> reservedUmbrella;
}
