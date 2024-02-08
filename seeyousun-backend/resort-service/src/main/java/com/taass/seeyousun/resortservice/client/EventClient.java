package com.taass.seeyousun.resortservice.client;

import com.taass.seeyousun.resortservice.dto.ApiResponseDTO;
import com.taass.seeyousun.resortservice.dto.EventDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "event-service")
public interface EventClient {
    @GetMapping("/api/v1/events/{resortId}")
    ResponseEntity<ApiResponseDTO<List<EventDTO>>> getEventForResort (@PathVariable Long resortId);
}
