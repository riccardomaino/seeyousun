package com.taass.seeyousun.resortservice.services;

import com.taass.seeyousun.resortservice.dto.ServiceDTO;
import com.taass.seeyousun.resortservice.mappers.Mapper;
import com.taass.seeyousun.resortservice.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final Mapper<ServiceDTO, com.taass.seeyousun.resortservice.model.Service> serviceMapper;

    public ServiceService(
            ServiceRepository serviceRepository,
            Mapper<ServiceDTO, com.taass.seeyousun.resortservice.model.Service> serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll()
                .stream()
                .map(serviceMapper::mapFrom)
                .toList();
    }
}
