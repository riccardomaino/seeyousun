package com.taass.seeyousun.reviewservice.service;

import com.taass.seeyousun.reviewservice.dto.ReviewDTO;
import com.taass.seeyousun.reviewservice.mappers.Mapper;
import com.taass.seeyousun.reviewservice.messaging.ReviewMessageProducer;
import com.taass.seeyousun.reviewservice.model.Review;
import com.taass.seeyousun.reviewservice.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {


    private final ReviewRepository reviewRepository;
    private final Mapper<ReviewDTO, Review> reviewMapper;
    private final ReviewMessageProducer reviewMessageProducer;

    public ReviewService(
            ReviewRepository reviewRepository,
            Mapper<ReviewDTO, Review> reviewMapper,
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
        Double averageRating = getAverageRating(review.getResortId());
        reviewMessageProducer.sendMessage(review.getResortId(), averageRating);
    }

    public Double getAverageRating(Long resortId){
        return reviewRepository.findAverageRatingOfResort(resortId);
    }
}
