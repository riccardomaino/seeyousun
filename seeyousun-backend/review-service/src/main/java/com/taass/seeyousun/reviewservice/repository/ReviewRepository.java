package com.taass.seeyousun.reviewservice.repository;

import com.taass.seeyousun.reviewservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByResortOrderByRating(Long resortId);
}
