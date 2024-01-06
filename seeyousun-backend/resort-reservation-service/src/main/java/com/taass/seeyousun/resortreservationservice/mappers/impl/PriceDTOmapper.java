package com.taass.seeyousun.resortreservationservice.mappers.impl;

import com.taass.seeyousun.resortreservationservice.DTO.PriceDTO;
import com.taass.seeyousun.resortreservationservice.mappers.Mapper;
import com.taass.seeyousun.resortreservationservice.model.ResortReservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PriceDTOmapper implements Mapper<PriceDTO, ResortReservation> {

    private final ModelMapper modelMapper;

    public PriceDTOmapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ResortReservation mapTo(PriceDTO priceDTO) {
        return modelMapper.map(priceDTO, ResortReservation.class);
    }

    @Override
    public PriceDTO mapFrom(ResortReservation resortReservation) {
        return  modelMapper.map(resortReservation, PriceDTO.class);
    }
}
