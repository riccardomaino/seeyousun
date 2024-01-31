package com.taass.seeyousun.reviewservice.service;

import com.taass.seeyousun.reviewservice.dto.ReviewDTO;
import com.taass.seeyousun.reviewservice.mappers.impl.ReviewMapper;
import com.taass.seeyousun.reviewservice.model.Review;
import com.taass.seeyousun.reviewservice.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {


    private ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;

    public List<ReviewDTO> getReviewForResort(Long resortId) {
        return reviewRepository.findByResortOrderByRating(resortId)
                .stream()
                .map(reviewMapper::mapTo)
                .toList();
    }

    public void createReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.mapFrom(reviewDTO);
        reviewRepository.save(review);
    }
}
