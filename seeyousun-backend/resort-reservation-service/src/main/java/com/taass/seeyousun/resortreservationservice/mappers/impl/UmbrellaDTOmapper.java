package com.taass.seeyousun.resortreservationservice.mappers.impl;

import com.taass.seeyousun.resortreservationservice.dto.UmbrellaDTO;
import com.taass.seeyousun.resortreservationservice.mappers.Mapper;
import com.taass.seeyousun.resortreservationservice.model.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UmbrellaDTOmapper implements Mapper<UmbrellaDTO, Reservation> {

    private final ModelMapper modelMapper;

    public UmbrellaDTOmapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Reservation mapTo(UmbrellaDTO umbrellaDTO) {
        return modelMapper.map(umbrellaDTO, Reservation.class);
    }

    @Override
    public UmbrellaDTO mapFrom(Reservation reservation) {
        return modelMapper.map(reservation, UmbrellaDTO.class);
    }
}
