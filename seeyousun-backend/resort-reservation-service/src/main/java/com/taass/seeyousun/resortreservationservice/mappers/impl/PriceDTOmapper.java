package com.taass.seeyousun.resortreservationservice.mappers.impl;

import com.taass.seeyousun.resortreservationservice.dto.ReservationStateDTO;
import com.taass.seeyousun.resortreservationservice.mappers.Mapper;
import com.taass.seeyousun.resortreservationservice.model.DailyReservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PriceDTOmapper implements Mapper<ReservationStateDTO, DailyReservation> {

    private final ModelMapper modelMapper;

    public PriceDTOmapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DailyReservation mapTo(ReservationStateDTO priceDTO) {
        return modelMapper.map(priceDTO, DailyReservation.class);
    }

    @Override
    public ReservationStateDTO mapFrom(DailyReservation dailyReservation) {
        return  modelMapper.map(dailyReservation, ReservationStateDTO.class);
    }
}
