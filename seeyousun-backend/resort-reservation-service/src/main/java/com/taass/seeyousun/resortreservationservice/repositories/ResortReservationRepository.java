package com.taass.seeyousun.resortreservationservice.repositories;

import com.taass.seeyousun.resortreservationservice.model.ResortReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ResortReservationRepository extends JpaRepository<ResortReservation,Long> {
    List<ResortReservation> findByResortAndDate(long resort, Date date);
}
