package com.taass.seeyousun.resortservice.client;

import com.taass.seeyousun.resortservice.dto.ApiResponseDTO;
import com.taass.seeyousun.resortservice.dto.ReviewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "review-service", url = "http://review-service:8083/api/v1/reviews")
public interface ReviewClient {

    @GetMapping("/{resortId}")
    ResponseEntity<ApiResponseDTO<List<ReviewDTO>>> getReviewsForResort(@PathVariable Long resortId);
}