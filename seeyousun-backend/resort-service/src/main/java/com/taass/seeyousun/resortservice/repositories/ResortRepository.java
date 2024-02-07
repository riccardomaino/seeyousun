package com.taass.seeyousun.resortservice.repositories;

import com.taass.seeyousun.resortservice.model.Resort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ResortRepository extends JpaRepository<Resort, Long> {
    Page<Resort> findByOrderByRatingDesc(Pageable pageable);

    List<Resort> findByNameContainingIgnoreCase(String name);

    List<Resort> findByLocationContainingIgnoreCase(String location);

    @Query("SELECT r FROM Resort r JOIN r.services s " +
            "WHERE lower(r.location) LIKE lower(concat('%', :location, '%')) AND s.name IN :services " +
            "GROUP BY r.id " +
            "HAVING COUNT(*) = :numServices")
    List<Resort> findByLocationContainingAndServicesIn(
            @Param("location") String location,
            @Param("services") Collection<String> services,
            @Param("numServices") Integer numServices);
}
