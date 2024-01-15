package com.tass.seeyousun.resortservice.repositories;

import com.tass.seeyousun.resortservice.model.Resort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResortRepository extends JpaRepository<Resort, Long> {

    @Query("SELECT r FROM Resort r ORDER BY r.rating DESC LIMIT 10")
    List<Resort> trovaResortMigliori();

    List<Resort> findByName(String name);

    List<Resort> findByLocation(String location);
}
