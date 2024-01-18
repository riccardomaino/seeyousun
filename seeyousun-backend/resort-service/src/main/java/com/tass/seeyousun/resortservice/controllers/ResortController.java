package com.tass.seeyousun.resortservice.controllers;

import com.tass.seeyousun.resortservice.dto.RequestByServiceAndLocationDTO;
import com.tass.seeyousun.resortservice.dto.ResortFullDTO;
import com.tass.seeyousun.resortservice.dto.ResortPresentationDTO;
import com.tass.seeyousun.resortservice.dto.ServiceDTO;
import com.tass.seeyousun.resortservice.enums.ServiceInterface;
import com.tass.seeyousun.resortservice.mappers.impl.ResortFullMapper;
import com.tass.seeyousun.resortservice.mappers.impl.ResortPresentationMapper;
import com.tass.seeyousun.resortservice.repositories.ResortRepository;
import com.tass.seeyousun.resortservice.services.ResortService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/resorts/v1")
public class ResortController {
    private final ResortService resortService;

    private final ResortFullMapper resortFullMapper;
    private final ResortPresentationMapper resortPresentationMapper;
    private final ResortRepository resortRepository;

    public ResortController(ResortService resortService, ResortFullMapper resortFullMapper, ResortPresentationMapper resortPresentationMapper, ResortRepository resortRepository){
        this.resortService = resortService;
        this.resortFullMapper = resortFullMapper;
        this.resortPresentationMapper = resortPresentationMapper;
        this.resortRepository = resortRepository;
    }

    @GetMapping("/popular")
    public List<ResortPresentationDTO> getPopularResort(){
        System.out.println("Get the first 10 popular resort...");
        return resortRepository.trovaResortMigliori()
                        .stream()
                        .map(resortPresentationMapper::mapFrom)
                        .toList();
    }

    /*
    Cerca il Resort per l'ID. Restituisce un Optional che viene convertito in null se non esiste nessun resort con quel ID
     */
    @GetMapping("/resort-by-id/{id}")
    public ResortFullDTO getResortInfo(@PathVariable Long id){
        System.out.println("Get the " + id + " resort");
        return resortRepository.findById(id)
                .map(resortFullMapper::mapFrom)
                .orElse(null);
    }

    @GetMapping("/resort-by-name/{name}")
    public List<ResortPresentationDTO> getResortByName(@PathVariable String name){
        System.out.println("Get the " + name + " resort");
        return resortRepository.findByNameContaining(name)
                .stream()
                .map(resortPresentationMapper::mapFrom)
                .toList();
    }

    @GetMapping("/resort-by-location/{location}")
    public List<ResortPresentationDTO> getResortByLocation(@PathVariable String location){
        System.out.println("Get the " + location + " resort");
        return resortRepository.findByLocationContaining(location)
                .stream()
                .map(resortPresentationMapper::mapFrom)
                .toList();
    }

    @PostMapping(name = "/resort-by-location-and-service",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ResortPresentationDTO> getResortByLocationAndService(@RequestBody RequestByServiceAndLocationDTO request){
        List<ServiceInterface> services = request.getServices()
                .stream()
                .map(ServiceDTO::getService)
                .toList();
        System.out.println(services);
        return resortRepository.findByLocationContaining(request.getLocation())
                .stream()
                .filter(r -> r.isOfferingService(services))
                .map(resortPresentationMapper::mapFrom)
                .toList();
    }

    @GetMapping("/services")
    public List<ServiceDTO> getAllService(){
        return  resortService.getAllServiceDTO();
    }
}
