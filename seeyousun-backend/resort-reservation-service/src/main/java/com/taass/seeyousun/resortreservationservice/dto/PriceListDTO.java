package com.taass.seeyousun.resortreservationservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceListDTO {
    // Lista ordinata per file dei prezzi degli ombrelloni: es. [15,10,10] prima fila 15Euro, seconda e terza 10Euro
    private List<Integer> umbrellaPrice;
    private Integer sunbedPrice;
}
