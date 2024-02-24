package com.taass.seeyousun.resortservice.mappers.impl;

import com.taass.seeyousun.resortservice.dto.ResortFullDTO;
import com.taass.seeyousun.resortservice.mappers.Mapper;
import com.taass.seeyousun.resortservice.model.Resort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * ResortFullMapper è una classe che permette di mappere oggetti "Resort" in oggetti "ResortFullDTO", e viceversa.
 */
@Component
public class ResortFullMapper implements Mapper<ResortFullDTO, Resort> {
    private final ModelMapper modelMapper;

    public ResortFullMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Resort mapTo(ResortFullDTO resortFullDTO) {
        return modelMapper.map(resortFullDTO, Resort.class);
    }

    @Override
    public ResortFullDTO mapFrom(Resort resort) {
        return modelMapper.map(resort, ResortFullDTO.class);
    }
}
