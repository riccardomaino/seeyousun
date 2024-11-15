package com.taass.seeyousun.resortservice.controllers;

import com.taass.seeyousun.resortservice.dto.*;
import com.taass.seeyousun.resortservice.services.ResortService;
import com.taass.seeyousun.resortservice.services.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/resorts")
//@CrossOrigin(origins = "http://localhost:4200")
public class ResortController {
    private final ResortService resortService;
    private final ServiceService serviceService;

    public ResortController(ResortService resortService, ServiceService serviceService){
        this.resortService = resortService;
        this.serviceService = serviceService;
    }

    @GetMapping("/popular")
    public ResponseEntity<ApiResponseDTO<PopularResortDTO>> getPopularResorts(
            @RequestParam(name = "pageNr", defaultValue = "0", required = false) int pageNr,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        PopularResortDTO popularResortDto = resortService.getPopularResorts(pageNr, pageSize);
        ApiResponseDTO<PopularResortDTO> response = ApiResponseDTO.<PopularResortDTO>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento dei resorts popolari avvenuto correttamente")
                .timestamp(new Date())
                .data(popularResortDto)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<ResortFullDTO>> getResortById(@PathVariable Long id){
        ResortFullDTO resortFullDto = resortService.getResortById(id);
        ApiResponseDTO<ResortFullDTO> response = ApiResponseDTO.<ResortFullDTO>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento del resort tramite id avvenuto correttamente")
                .timestamp(new Date())
                .data(resortFullDto)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-name")
    public ResponseEntity<ApiResponseDTO<List<ResortDTO>>> getResortsByName(@RequestParam("name") String name){
        List<ResortDTO> resortDtoList = resortService.getResortsByName(name);
        ApiResponseDTO<List<ResortDTO>> response = ApiResponseDTO.<List<ResortDTO>>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento dei resorts tramite nome avvenuto correttamente")
                .timestamp(new Date())
                .data(resortDtoList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-location")
    public ResponseEntity<ApiResponseDTO<List<ResortDTO>>> getResortsByLocation(@RequestParam("location") String location){
        List<ResortDTO> resortDtoList = resortService.getResortsByLocation(location);
        ApiResponseDTO<List<ResortDTO>> response = ApiResponseDTO.<List<ResortDTO>>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento dei resorts tramite location avvenuto correttamente")
                .timestamp(new Date())
                .data(resortDtoList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-location-and-services")
    public ResponseEntity<ApiResponseDTO<List<ResortDTO>>> getResortsByLocationAndServices(
            @RequestParam(name = "location") String location,
            @RequestParam(name = "services") List<String> services){
        List<ResortDTO> resortDtoList = resortService.getResortsByLocationAndServices(location, services);
        ApiResponseDTO<List<ResortDTO>> response = ApiResponseDTO.<List<ResortDTO>>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento dei resorts tramite location e servizi avvenuto correttamente")
                .timestamp(new Date())
                .data(resortDtoList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/services")
    public ResponseEntity<ApiResponseDTO<List<ServiceDTO>>> getAllServices(){
        List<ServiceDTO> serviceDtoList = serviceService.getAllServices();
        ApiResponseDTO<List<ServiceDTO>> response = ApiResponseDTO.<List<ServiceDTO>>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento di tutti i servizi avvenuto correttamente")
                .timestamp(new Date())
                .data(serviceDtoList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/dimension/{resortId}")
    public ResponseEntity<ApiResponseDTO<DimensionDTO>> getResortDimension(@PathVariable Long resortId){
        DimensionDTO dimensionDTO = resortService.getResortDimension(resortId);
        ApiResponseDTO<DimensionDTO> response = ApiResponseDTO.<DimensionDTO>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento delle dimensioni della mappa del resort avvenuto correttamente")
                .timestamp(new Date())
                .data(dimensionDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/pricing/{resortId}")
    public ResponseEntity<ApiResponseDTO<PriceListDTO>> getResortPricing(
            @PathVariable Long resortId,
            @RequestParam String date
    ){
        PriceListDTO priceListDTO = resortService.getResortPricing(resortId, LocalDate.parse(date));
        ApiResponseDTO<PriceListDTO> response = ApiResponseDTO.<PriceListDTO>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento del listino prezzi del resort avvenuto con successo")
                .timestamp(new Date())
                .data(priceListDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/information/{resortId}")
    public ResponseEntity<ApiResponseDTO<ReservationStateDTO>> getReservationInformation(
            @PathVariable Long resortId,
            @RequestParam String date) {
        ReservationStateDTO reservationDTO = resortService.getReservationInformation(resortId, LocalDate.parse(date));
        ApiResponseDTO<ReservationStateDTO> response = ApiResponseDTO.<ReservationStateDTO>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento del listino prezzi del resort avvenuto correttamente")
                .timestamp(new Date())
                .data(reservationDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<ApiResponseDTO<Boolean>> checkResortById(@PathVariable Long id){
        Boolean isPresent = resortService.checkResortById(id);
        ApiResponseDTO<Boolean> response = ApiResponseDTO.<Boolean>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, controllo esistenza del resort tramite id avvenuto correttamente")
                .timestamp(new Date())
                .data(isPresent)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("name/{resortId}")
    public ResponseEntity<ApiResponseDTO<String>> getResortName(@PathVariable Long resortId){
        String resortName = resortService.getResortName(resortId);
        ApiResponseDTO<String> response = ApiResponseDTO.<String>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento nome del resort avvenuto correttamente")
                .timestamp(new Date())
                .data(resortName)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
