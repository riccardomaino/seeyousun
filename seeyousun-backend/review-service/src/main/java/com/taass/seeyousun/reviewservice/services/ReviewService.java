package com.taass.seeyousun.reviewservice.services;

import com.taass.seeyousun.reviewservice.client.ResortClient;
import com.taass.seeyousun.reviewservice.dto.ApiResponseDTO;
import com.taass.seeyousun.reviewservice.dto.ReviewDTO;
import com.taass.seeyousun.reviewservice.exceptions.ResortNotFoundException;
import com.taass.seeyousun.reviewservice.mappers.impl.ReviewMapper;
import com.taass.seeyousun.reviewservice.messaging.ReviewMessageProducer;
import com.taass.seeyousun.reviewservice.model.Review;
import com.taass.seeyousun.reviewservice.repository.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewMessageProducer reviewMessageProducer;
    private final ResortClient resortClient;


    public ReviewService(
            ReviewRepository reviewRepository,
            ReviewMapper reviewMapper,
            ReviewMessageProducer reviewMessageProducer,
            ResortClient resortClient
    ) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.reviewMessageProducer = reviewMessageProducer;
        this.resortClient = resortClient;
    }

    public List<ReviewDTO> getReviewForResort(Long resortId) {
        return reviewRepository.findByResortIdOrderByRating(resortId)
                .stream()
                .map(reviewMapper::mapFrom)
                .toList();
    }

    public void createReview(ReviewDTO reviewDTO) {
        ResponseEntity<ApiResponseDTO<Boolean>> response = resortClient.checkResortById(reviewDTO.getResortId());
        if(Objects.requireNonNull(response.getBody()).getData()){
            Review review = reviewMapper.mapTo(reviewDTO);
            reviewRepository.save(review);
            BigDecimal averageRating = getAverageRating(review.getResortId());
            // BigDecimal averageRating = new BigDecimal(averageRatingFromDatabase).setScale(1, RoundingMode.CEILING);
            reviewMessageProducer.sendMessage(review.getResortId(), averageRating);
        }else{
            throw new ResortNotFoundException(String.format("Nessun resorts trovato con id: '%d'", reviewDTO.getResortId()));
        }

    }

    public BigDecimal getAverageRating(Long resortId){
        return reviewRepository.findAverageRatingOfResort(resortId);
    }
}
