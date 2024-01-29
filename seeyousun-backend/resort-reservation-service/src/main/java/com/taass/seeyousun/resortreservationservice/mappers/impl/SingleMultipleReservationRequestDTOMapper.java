package com.taass.seeyousun.resortreservationservice.mappers.impl;

import com.taass.seeyousun.resortreservationservice.dto.MultipleReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.dto.SingleReservationRequestDTO;
import com.taass.seeyousun.resortreservationservice.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SingleMultipleReservationRequestDTOMapper implements Mapper<SingleReservationRequestDTO, MultipleReservationRequestDTO> {

    private final ModelMapper modelMapper;

    public SingleMultipleReservationRequestDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public MultipleReservationRequestDTO mapTo(SingleReservationRequestDTO singleReservationRequestDTO) {
        return modelMapper.map(singleReservationRequestDTO, MultipleReservationRequestDTO.class);
    }

    @Override
    public SingleReservationRequestDTO mapFrom(MultipleReservationRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, SingleReservationRequestDTO.class);
    }
}
