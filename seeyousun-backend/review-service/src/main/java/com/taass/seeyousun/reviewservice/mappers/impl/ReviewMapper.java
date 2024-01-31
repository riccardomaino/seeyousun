package com.taass.seeyousun.reviewservice.mappers.impl;

import com.taass.seeyousun.reviewservice.dto.ReviewDTO;
import com.taass.seeyousun.reviewservice.mappers.Mapper;
import com.taass.seeyousun.reviewservice.model.Review;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements Mapper<Review, ReviewDTO> {
    private final ModelMapper modelMapper;

    public ReviewMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewDTO mapTo(Review review) {
        return modelMapper.map(review, ReviewDTO.class);
    }

    @Override
    public Review mapFrom(ReviewDTO reviewDTO) {
        return modelMapper.map(reviewDTO, Review.class);
    }
}
