package com.taass.seeyousun.resortservice.mappers.impl;

import com.taass.seeyousun.resortservice.dto.PriceListDTO;
import com.taass.seeyousun.resortservice.mappers.Mapper;
import com.taass.seeyousun.resortservice.model.PricePeriod;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PriceListMapper implements Mapper<PriceListDTO, PricePeriod> {
    private final ModelMapper modelMapper;

    public PriceListMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PricePeriod mapTo(PriceListDTO priceListDTO) {
        return modelMapper.map(priceListDTO,PricePeriod.class);
    }

    @Override
    public PriceListDTO mapFrom(PricePeriod pricePeriod) {
        return modelMapper.map(pricePeriod, PriceListDTO.class);
    }
}
