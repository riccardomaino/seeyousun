package com.taass.seeyousun.resortservice.mappers.impl;

import com.taass.seeyousun.resortservice.dto.ResortDTO;
import com.taass.seeyousun.resortservice.mappers.Mapper;
import com.taass.seeyousun.resortservice.model.Resort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ResortMapper implements Mapper<ResortDTO, Resort> {


    private final ModelMapper modelMapper;

    public ResortMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Resort mapTo(ResortDTO resortDTO) {
        return modelMapper.map(resortDTO, Resort.class);
    }

    @Override
    public ResortDTO mapFrom(Resort resort) {
        return modelMapper.map(resort, ResortDTO.class);
    }
}
