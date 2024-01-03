package com.tass.seeyousun.resortservice.mappers.impl;

import com.tass.seeyousun.resortservice.dto.ResortFullDTO;
import com.tass.seeyousun.resortservice.dto.ResortPresentationDTO;
import com.tass.seeyousun.resortservice.mappers.Mapper;
import com.tass.seeyousun.resortservice.model.Resort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ResortPresentationMapper implements Mapper<ResortPresentationDTO, Resort> {


    private final ModelMapper modelMapper;

    public ResortPresentationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Resort mapTo(ResortPresentationDTO resortPresentationDTO) {
        return modelMapper.map(resortPresentationDTO, Resort.class);
    }

    @Override
    public ResortPresentationDTO mapFrom(Resort resort) {
        return modelMapper.map(resort, ResortPresentationDTO.class);
    }
}
