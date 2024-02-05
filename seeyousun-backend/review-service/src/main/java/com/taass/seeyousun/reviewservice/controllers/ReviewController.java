package com.taass.seeyousun.reviewservice.controllers;


import com.taass.seeyousun.reviewservice.dto.ApiResponseDTO;
import com.taass.seeyousun.reviewservice.dto.ReviewDTO;
import com.taass.seeyousun.reviewservice.service.ReviewService;
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
    public ResponseEntity<ApiResponseDTO<List<ReviewDTO>>> getReviewForResort(@PathVariable Long resortId){
        List<ReviewDTO> resultReviews = reviewService.getReviewForResort(resortId);
        ApiResponseDTO<List<ReviewDTO>> response = ApiResponseDTO.<List<ReviewDTO>>builder()
                .statusCode(200)
                .message("found successful")
                .data(resultReviews)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDTO<ReviewDTO>> createReview(@RequestBody ReviewDTO reviewDTO){
        reviewService.createReview(reviewDTO);

        ApiResponseDTO<ReviewDTO> response = ApiResponseDTO.<ReviewDTO>builder()
                .statusCode(201)
                .message("created successful")
                .data(null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
