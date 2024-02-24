package com.taass.seeyousun.resortservice.mappers.impl;

import com.taass.seeyousun.resortservice.dto.ServiceDTO;
import com.taass.seeyousun.resortservice.mappers.Mapper;
import com.taass.seeyousun.resortservice.model.Service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper implements Mapper<ServiceDTO, Service> {
    private final ModelMapper modelMapper;

    public ServiceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Service mapTo(ServiceDTO serviceDTO) {
        return modelMapper.map(serviceDTO, Service.class);
    }

    @Override
    public ServiceDTO mapFrom(Service service) {
        return modelMapper.map(service, ServiceDTO.class);
    }
}
