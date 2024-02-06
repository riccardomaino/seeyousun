package com.taass.seeyousun.reviewservice.repository;

import com.taass.seeyousun.reviewservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByResortIdOrderByRating(Long resortId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.resortId = :resortId")
    Double findAverageRatingOfResort(@Param("resortId")Long resortId);
}
