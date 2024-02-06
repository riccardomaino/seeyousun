package com.taass.seeyousun.resortreservationservice.mappers.impl;

import com.taass.seeyousun.resortreservationservice.dto.UmbrellaDTO;
import com.taass.seeyousun.resortreservationservice.mappers.Mapper;
import com.taass.seeyousun.resortreservationservice.model.DailyReservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UmbrellaDTOmapper implements Mapper<UmbrellaDTO, DailyReservation> {

    private final ModelMapper modelMapper;

    public UmbrellaDTOmapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DailyReservation mapTo(UmbrellaDTO umbrellaDTO) {
        return modelMapper.map(umbrellaDTO, DailyReservation.class);
    }

    @Override
    public UmbrellaDTO mapFrom(DailyReservation dailyReservation) {
        return modelMapper.map(dailyReservation, UmbrellaDTO.class);
    }
}
