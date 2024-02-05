package com.taass.seeyousun.resortreservationservice.mappers.impl;

import com.taass.seeyousun.resortreservationservice.dto.ReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.mappers.Mapper;
import com.taass.seeyousun.resortreservationservice.model.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationRequestDTOmapper implements Mapper<ReservationRequestDTO, Reservation> {
    private final ModelMapper modelMapper;

    public ReservationRequestDTOmapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Reservation mapTo(ReservationRequestDTO singleReservationRequestDTO) {
        return modelMapper.map(singleReservationRequestDTO, Reservation.class);
    }

    @Override
    public ReservationRequestDTO mapFrom(Reservation reservation) {
        return modelMapper.map(reservation, ReservationRequestDTO.class);
    }
}
