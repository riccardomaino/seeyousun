package com.taass.seeyousun.resortreservationservice.mappers.impl;

import com.taass.seeyousun.resortreservationservice.DTO.MultipleReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.mappers.Mapper;
import com.taass.seeyousun.resortreservationservice.model.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MultipleReservationRequestDTOmapper implements Mapper<MultipleReservationRequestDTO, Reservation> {
    private final ModelMapper modelMapper;

    public MultipleReservationRequestDTOmapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Reservation mapTo(MultipleReservationRequestDTO singleReservationRequestDTO) {
        return modelMapper.map(singleReservationRequestDTO, Reservation.class);
    }

    @Override
    public MultipleReservationRequestDTO mapFrom(Reservation reservation) {
        return modelMapper.map(reservation, MultipleReservationRequestDTO.class);
    }
}
