package com.taass.seeyousun.reviewservice.controllers;


import com.taass.seeyousun.reviewservice.dto.ApiResponseDTO;
import com.taass.seeyousun.reviewservice.dto.ReviewDTO;
import com.taass.seeyousun.reviewservice.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{resortId}")
    public ResponseEntity<ApiResponseDTO<List<ReviewDTO>>> getReviewsForResort(@PathVariable Long resortId){
        List<ReviewDTO> resultReviews = reviewService.getReviewForResort(resortId);
        ApiResponseDTO<List<ReviewDTO>> response = ApiResponseDTO.<List<ReviewDTO>>builder()
                .statusCode(200)
                .message("Successo, ottenimento delle reviews effettuato correttamente")
                .data(resultReviews)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDTO<Object>> createReview(@RequestBody ReviewDTO reviewDTO){
        reviewService.createReview(reviewDTO);
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .statusCode(201)
                .message("Successo, creazione della review effettuata correttamente")
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
