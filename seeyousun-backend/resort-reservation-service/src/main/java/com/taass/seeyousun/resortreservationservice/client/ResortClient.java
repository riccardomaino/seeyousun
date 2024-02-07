package com.taass.seeyousun.resortreservationservice.client;

import com.taass.seeyousun.resortreservationservice.dto.ApiResponseDTO;
import com.taass.seeyousun.resortreservationservice.dto.DimensionDTO;
import com.taass.seeyousun.resortreservationservice.dto.PriceListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "resort-service", url = "http://resort-service:8081/api/v1/resorts")
public interface ResortClient {
    @GetMapping("/dimension/{resortId}")
    ResponseEntity<ApiResponseDTO<DimensionDTO>> getResortDimension(@PathVariable long resortId);

    @GetMapping("/pricing/{resortId}")
    ResponseEntity<ApiResponseDTO<PriceListDTO>> getResortPrice(@PathVariable long resortId, @RequestParam String date);
}