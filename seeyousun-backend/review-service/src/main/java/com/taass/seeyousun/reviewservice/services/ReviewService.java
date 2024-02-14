package com.taass.seeyousun.reviewservice.services;

import com.taass.seeyousun.reviewservice.client.ResortClient;
import com.taass.seeyousun.reviewservice.dto.ReviewDTO;
import com.taass.seeyousun.reviewservice.dto.ReviewFullDTO;
import com.taass.seeyousun.reviewservice.exceptions.ResortNotFoundException;
import com.taass.seeyousun.reviewservice.mappers.impl.ReviewFullMapper;
import com.taass.seeyousun.reviewservice.mappers.impl.ReviewMapper;
import com.taass.seeyousun.reviewservice.messaging.ReviewMessageProducer;
import com.taass.seeyousun.reviewservice.model.Review;
import com.taass.seeyousun.reviewservice.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewFullMapper reviewFullMapper;
    private final ReviewMapper reviewMapper;
    private final ReviewMessageProducer reviewMessageProducer;
    private final ResortClient resortClient;


    public ReviewService(
            ReviewRepository reviewRepository,
            ReviewFullMapper reviewFullMapper,
            ReviewMapper reviewMapper,
            ReviewMessageProducer reviewMessageProducer,
            ResortClient resortClient
    ) {
        this.reviewRepository = reviewRepository;
        this.reviewFullMapper = reviewFullMapper;
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

    public void createReview(ReviewFullDTO reviewFullDTO) {
        Boolean resortExist = Objects.requireNonNull(resortClient.checkResortById(reviewFullDTO.getResortId())
                .getBody()).getData();
        if(resortExist){
            Review review = reviewFullMapper.mapTo(reviewFullDTO);
            reviewRepository.save(review);
            BigDecimal averageRating = getAverageRating(review.getResortId());
            reviewMessageProducer.sendMessage(review.getResortId(), averageRating);
        }else{
            throw new ResortNotFoundException(String.format("Nessun resorts trovato con id: '%d'", reviewFullDTO.getResortId()));
        }

    }

    public BigDecimal getAverageRating(Long resortId){
        return reviewRepository.findAverageRatingOfResort(resortId);
    }
}
