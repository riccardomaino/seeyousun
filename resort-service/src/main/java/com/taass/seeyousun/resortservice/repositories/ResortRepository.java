package com.taass.seeyousun.resortservice.repositories;

import com.taass.seeyousun.resortservice.model.PricePeriod;
import com.taass.seeyousun.resortservice.model.Resort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResortRepository extends JpaRepository<Resort, Long> {
    Page<Resort> findByOrderByRatingDesc(Pageable pageable);

    List<Resort> findByNameContainingIgnoreCase(String name);

    List<Resort> findByLocationContainingIgnoreCase(String location);

    @Query("SELECT p FROM Resort r JOIN r.pricePeriodList p WHERE r.id = :resortId and (:date BETWEEN p.periodInitialDate AND p.periodFinalDate) ")
    Optional<PricePeriod> findPricePeriod(Long resortId, LocalDate date);

    @Query("SELECT r FROM Resort r JOIN r.services s " +
            "WHERE lower(r.location) LIKE lower(concat('%', :location, '%')) AND s.name IN :services " +
            "GROUP BY r.id " +
            "HAVING COUNT(*) = :numServices")
    List<Resort> findByLocationContainingAndServicesIn(
            @Param("location") String location,
            @Param("services") Collection<String> services,
            @Param("numServices") Integer numServices);

    @Query("SELECT r.name FROM Resort r WHERE r.id = :resortId")
    Optional<String> findNameById(Long resortId);
}
