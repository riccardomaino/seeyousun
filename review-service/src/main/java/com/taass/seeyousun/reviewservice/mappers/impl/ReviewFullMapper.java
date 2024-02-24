package com.taass.seeyousun.reviewservice.mappers.impl;

import com.taass.seeyousun.reviewservice.dto.ReviewFullDTO;
import com.taass.seeyousun.reviewservice.mappers.Mapper;
import com.taass.seeyousun.reviewservice.model.Review;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewFullMapper implements Mapper<ReviewFullDTO, Review> {
    private final ModelMapper modelMapper;

    public ReviewFullMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Review mapTo(ReviewFullDTO reviewFullDTO) {
        return modelMapper.map(reviewFullDTO, Review.class);
    }

    @Override
    public ReviewFullDTO mapFrom(Review review) {
        return modelMapper.map(review, ReviewFullDTO.class);
    }
}
