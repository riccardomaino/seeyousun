package com.taass.seeyousun.resortreservationservice.repositories;

import com.taass.seeyousun.resortreservationservice.dto.ApiResponseDTO;
import com.taass.seeyousun.resortreservationservice.dto.DimensionDTO;
import com.taass.seeyousun.resortreservationservice.dto.PriceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "resort-service", url = "http://resort-service:8081/api/v1/resorts")
public interface ResortClient {
    @GetMapping("/dimension/{resortId}")
    ResponseEntity<ApiResponseDTO<DimensionDTO>> getResortDimension(@PathVariable long resortId);

    @GetMapping("/pricing/{resortId}/{date}")
    ResponseEntity<ApiResponseDTO<PriceDTO>> getResortPrice(@PathVariable long resortId, @PathVariable String date);
}
