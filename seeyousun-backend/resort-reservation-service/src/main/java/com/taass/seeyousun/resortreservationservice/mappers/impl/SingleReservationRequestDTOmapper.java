package com.taass.seeyousun.resortreservationservice.mappers.impl;

import com.taass.seeyousun.resortreservationservice.DTO.SingleReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.mappers.Mapper;
import com.taass.seeyousun.resortreservationservice.model.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SingleReservationRequestDTOmapper implements Mapper<SingleReservationRequestDTO, Reservation> {
    private final ModelMapper modelMapper;

    public SingleReservationRequestDTOmapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Reservation mapTo(SingleReservationRequestDTO singleReservationRequestDTO) {
        return modelMapper.map(singleReservationRequestDTO, Reservation.class);
    }

    @Override
    public SingleReservationRequestDTO mapFrom(Reservation reservation) {
        return modelMapper.map(reservation, SingleReservationRequestDTO.class);
    }
}
