package com.taass.seeyousun.resortservice.client;

import com.taass.seeyousun.resortservice.dto.ApiResponseDTO;
import com.taass.seeyousun.resortservice.dto.UmbrellaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "resort-reservation-service")
public interface ResortReservationClient {
    @GetMapping("/api/v1/resort-reservations/umbrella/{resortId}")
    ResponseEntity<ApiResponseDTO<List<UmbrellaDTO>>> getReservedUmbrellaForResortAndDate(@PathVariable Long resortId, @RequestParam String date);
}
