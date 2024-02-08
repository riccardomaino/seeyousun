package com.taass.seeyousun.reviewservice.services;

import com.taass.seeyousun.reviewservice.dto.ReviewDTO;
import com.taass.seeyousun.reviewservice.mappers.impl.ReviewMapper;
import com.taass.seeyousun.reviewservice.messaging.ReviewMessageProducer;
import com.taass.seeyousun.reviewservice.model.Review;
import com.taass.seeyousun.reviewservice.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewMessageProducer reviewMessageProducer;

    public ReviewService(
            ReviewRepository reviewRepository,
            ReviewMapper reviewMapper,
            ReviewMessageProducer reviewMessageProducer) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    public List<ReviewDTO> getReviewForResort(Long resortId) {
        return reviewRepository.findByResortIdOrderByRating(resortId)
                .stream()
                .map(reviewMapper::mapFrom)
                .toList();
    }

    public void createReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.mapTo(reviewDTO);
        reviewRepository.save(review);
        Double averageRatingFromDatabase = getAverageRating(review.getResortId());
        BigDecimal averageRating = new BigDecimal(averageRatingFromDatabase)
                .setScale(1, RoundingMode.CEILING);
        reviewMessageProducer.sendMessage(review.getResortId(), averageRating);
    }

    public Double getAverageRating(Long resortId){
        return reviewRepository.findAverageRatingOfResort(resortId);
    }
}
