package com.taass.seeyousun.resortreservationservice.mappers.impl;

import com.taass.seeyousun.resortreservationservice.DTO.UmbrellaDTO;
import com.taass.seeyousun.resortreservationservice.mappers.Mapper;
import com.taass.seeyousun.resortreservationservice.model.ResortReservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UmbrellaDTOmapper implements Mapper<UmbrellaDTO, ResortReservation> {

    private final ModelMapper modelMapper;

    public UmbrellaDTOmapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ResortReservation mapTo(UmbrellaDTO umbrellaDTO) {
        return modelMapper.map(umbrellaDTO, ResortReservation.class);
    }

    @Override
    public UmbrellaDTO mapFrom(ResortReservation resortReservation) {
        return modelMapper.map(resortReservation, UmbrellaDTO.class);
    }
}
