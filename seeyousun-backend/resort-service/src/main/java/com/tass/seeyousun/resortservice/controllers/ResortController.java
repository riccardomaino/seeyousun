package com.tass.seeyousun.resortservice.controllers;

import com.tass.seeyousun.resortservice.dto.ResortFullDTO;
import com.tass.seeyousun.resortservice.dto.ResortPresentationDTO;
import com.tass.seeyousun.resortservice.dto.ServiceDTO;
import com.tass.seeyousun.resortservice.mappers.impl.*;
import com.tass.seeyousun.resortservice.repositories.ResortRepository;
import com.tass.seeyousun.resortservice.services.ResortService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/getResortById/{id}")
    public ResortFullDTO getResortInfo(@PathVariable Long id){
        System.out.println("Get the " + id + " resort");
        return resortRepository.findById(id)
                .map(resortFullMapper::mapFrom)
                .orElse(null);
    }

    @GetMapping("/getResortByName/{name}")
    public List<ResortPresentationDTO> getResortByName(@PathVariable String name){
        System.out.println("Get the " + name + " resort");
        return resortRepository.findByName(name)
                .stream()
                .map(resortPresentationMapper::mapFrom)
                .toList();
    }

    @GetMapping("/getResortByLocation/{location}")
    public List<ResortPresentationDTO> getResortByLocation(@PathVariable String location){
        System.out.println("Get the " + location + " resort");
        return resortRepository.findByLocation(location)
                .stream()
                .map(resortPresentationMapper::mapFrom)
                .toList();
    }

    //TODO fare api per restituire tutti i possibili servizi
    @GetMapping("/getAllService")
    public List<ServiceDTO> getAllService(){
        return  resortService.getAllServiceDTO();
    }
}
