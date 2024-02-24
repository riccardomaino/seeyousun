package com.taass.seeyousun.reviewservice.client;

import com.taass.seeyousun.reviewservice.dto.ApiResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "resort-service")
public interface ResortClient {
    @GetMapping("api/v1/resorts/exists/{resortId}")
    ResponseEntity<ApiResponseDTO<Boolean>> checkResortById(@PathVariable Long resortId);
}
