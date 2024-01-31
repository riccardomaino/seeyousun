package com.taass.seeyousun.reviewservice.mappers.impl;

import com.taass.seeyousun.reviewservice.dto.ReviewDTO;
import com.taass.seeyousun.reviewservice.mappers.Mapper;
import com.taass.seeyousun.reviewservice.model.Review;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements Mapper<ReviewDTO, Review> {
    private final ModelMapper modelMapper;

    public ReviewMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Review mapTo(ReviewDTO reviewDTO) {
        return modelMapper.map(reviewDTO, Review.class);
    }

    @Override
    public ReviewDTO mapFrom(Review review) {
        return modelMapper.map(review, ReviewDTO.class);
    }
}
