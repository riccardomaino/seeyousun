package com.taass.seeyousun.resortservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Usato per ritornare informazioni sulla prenotazione degli ombrelloni
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceDTO {
    //lista ordinata per file dei prezzi degli ombrelloni:
    //[15,10,10] in prima fila si paga 15, in seconda 10 in terza 10
    private List<Integer> umbrellaPrice;

    private Integer sunbedPrice;
}
