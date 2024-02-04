package com.taass.seeyousun.resortreservationservice.repositories;

import com.taass.seeyousun.resortreservationservice.dto.DimensionDTO;
import com.taass.seeyousun.resortreservationservice.dto.PriceDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDate;

public interface ResortClient {
    @GetExchange(value = "/dimension/{resortId}")
    DimensionDTO getResortDimension(@PathVariable long resortId);

    @GetExchange(value = "/pricing/{resortId}/{date}")
    PriceDTO getResortPrice(@PathVariable long resortId, @PathVariable LocalDate date);
}
