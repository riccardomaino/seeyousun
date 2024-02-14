package com.taass.seeyousun.reviewservice.controllers;


import com.taass.seeyousun.reviewservice.dto.ApiResponseDTO;
import com.taass.seeyousun.reviewservice.dto.ReviewDTO;
import com.taass.seeyousun.reviewservice.dto.ReviewFullDTO;
import com.taass.seeyousun.reviewservice.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        List<ReviewDTO> reviews = reviewService.getReviewForResort(resortId);
        ApiResponseDTO<List<ReviewDTO>> response = ApiResponseDTO.<List<ReviewDTO>>builder()
                .statusCode(200)
                .success(true)
                .message("Successo, ottenimento delle reviews effettuato correttamente")
                .timestamp(new Date())
                .data(reviews)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDTO<Object>> createReview(
            @RequestBody ReviewFullDTO reviewFullDTO,
            @RequestHeader("X-User-UID") String userUid,
            @RequestHeader("X-User-Name") String userName
    ){
        reviewFullDTO.setUserUid(userUid);
        reviewFullDTO.setUsername(userName);
        reviewService.createReview(reviewFullDTO);
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .statusCode(201)
                .success(true)
                .message("Successo, creazione della review effettuata correttamente")
                .timestamp(new Date())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
