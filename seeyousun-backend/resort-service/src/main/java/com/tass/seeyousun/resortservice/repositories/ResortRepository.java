package com.tass.seeyousun.resortservice.repositories;

import com.tass.seeyousun.resortservice.model.Resort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResortRepository extends JpaRepository<Resort, Long> {
}
