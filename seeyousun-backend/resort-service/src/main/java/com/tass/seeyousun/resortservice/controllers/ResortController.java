package com.tass.seeyousun.resortservice.controllers;

import com.tass.seeyousun.resortservice.dto.ResortDTO;
import com.tass.seeyousun.resortservice.mappers.impl.ResortMapper;
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

    private final ResortMapper resortMapper;
    private final ResortRepository resortRepository;

    public ResortController(ResortService resortService, ResortMapper resortMapper, ResortRepository resortRepository){
        this.resortService = resortService;
        this.resortMapper = resortMapper;
        this.resortRepository = resortRepository;
    }

    @GetMapping("/popular")
    public List<ResortDTO> getPopularResort(){
        System.out.println("Get the first 10 popular resort...");
        return resortRepository.trovaResortMigliori()
                        .stream()
                        .map(resortMapper::mapFrom)
                        .toList();
    }

    /*
    Cerca il Resort per l'ID. Restituisce un Optional che viene convertito in null se non esiste nessun resort con quel ID
     */
    @GetMapping("/getResortById/{id}")
    public ResortDTO getResortInfo(@PathVariable Long id){
        System.out.println("Get the " + id + " resort");
        return resortRepository.findById(id)
                .map(resortMapper::mapFrom)
                .orElse(null);
    }

    @GetMapping("/getResortByName/{name}")
    public List<ResortDTO> getResortByName(@PathVariable String name){
        System.out.println("Get the " + name + " resort");
        return resortRepository.findByName(name)
                .stream()
                .map(resortMapper::mapFrom)
                .toList();
    }

    @GetMapping("/getResortByLocation/{location}")
    public List<ResortDTO> getResortByLocation(@PathVariable String location){
        System.out.println("Get the " + location + " resort");
        return resortRepository.findByLocation(location)
                .stream()
                .map(resortMapper::mapFrom)
                .toList();
    }
}
