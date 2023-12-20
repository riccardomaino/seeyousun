package com.tass.seeyousun.resortservice.controllers;

import com.tass.seeyousun.resortservice.dto.ResortDTO;
import com.tass.seeyousun.resortservice.services.ResortService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resorts/v1")
public class ResortController {
    private final ResortService resortService;
    public ResortController(ResortService resortService){
        this.resortService = resortService;
    }

    @GetMapping("/popular")
    public List<ResortDTO> getPopularResort(){
        // TODO: implementare sulla base della recensione più alta
        return null;
    }

    @GetMapping("/resort/{id}")
    public ResortDTO getResortInfo(@PathVariable String id){
        return null;
    }

    @GetMapping("/resort/{name}")
    public ResortDTO getResortByName(@PathVariable String name){
        return null;
    }

    @GetMapping("/resort/{location}")
    public List<ResortDTO> getResortByLocation(@PathVariable String location){
        return null;
    }
}
