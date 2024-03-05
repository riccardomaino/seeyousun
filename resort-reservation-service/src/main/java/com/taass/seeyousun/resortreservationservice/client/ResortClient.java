package com.taass.seeyousun.resortreservationservice.client;

import com.taass.seeyousun.resortreservationservice.dto.ApiResponseDTO;
import com.taass.seeyousun.resortreservationservice.dto.DimensionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "resort-service")
public interface ResortClient {
    @GetMapping("api/v1/resorts/dimension/{resortId}")
    ResponseEntity<ApiResponseDTO<DimensionDTO>> getResortDimension(@PathVariable Long resortId);

    @GetMapping("api/v1/resorts/name/{resortId}")
    ResponseEntity<ApiResponseDTO<String>> getResortName(@PathVariable Long resortId);
}