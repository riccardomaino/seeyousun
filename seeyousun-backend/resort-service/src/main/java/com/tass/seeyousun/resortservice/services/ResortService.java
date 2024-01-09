package com.tass.seeyousun.resortservice.services;

import com.tass.seeyousun.resortservice.dto.ServiceDTO;
import com.tass.seeyousun.resortservice.enums.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResortService {
    public ResortService() {
    }

    public List<ServiceDTO> getAllServiceDTO() {
        List<ServiceDTO> serviceDTOList = new ArrayList<>();
        for(Accessibilita a : Accessibilita.values()){
            serviceDTOList.add(new ServiceDTO(a.name(), "Accessibilità"));
        }
        for(Benessere a : Benessere.values()){
            serviceDTOList.add(new ServiceDTO(a.name(), "Benessere"));
        }
        for(Ristorazione a : Ristorazione.values()){
            serviceDTOList.add(new ServiceDTO(a.name(), "Ristorazione"));
        }
        for(Servizio a : Servizio.values()){
            serviceDTOList.add(new ServiceDTO(a.name(), "Servizio"));
        }
        for(ServiziPerFamiglie a : ServiziPerFamiglie.values()){
            serviceDTOList.add(new ServiceDTO(a.name(), "ServiziPerFamiglie"));
        }
        for(SportEAttivita a : SportEAttivita.values()){
            serviceDTOList.add(new ServiceDTO(a.name(), "SportEAttivita"));
        }

        return serviceDTOList;
    }
}
