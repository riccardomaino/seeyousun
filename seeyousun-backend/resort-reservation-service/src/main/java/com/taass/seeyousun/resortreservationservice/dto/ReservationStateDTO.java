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
public class ReservationStateDTO {

    private DimensionDTO dimension;

    private PriceDTO priceList;

    private List<UmbrellaDTO> reservedUmbrella;

}
