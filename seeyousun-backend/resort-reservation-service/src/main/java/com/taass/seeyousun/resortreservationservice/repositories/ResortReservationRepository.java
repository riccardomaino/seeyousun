package com.taass.seeyousun.resortreservationservice.repositories;

import com.taass.seeyousun.resortreservationservice.model.ResortReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ResortReservationRepository extends JpaRepository<ResortReservation,Long> {
    List<ResortReservation> findByResortIdAndDate(long resort, LocalDate date);

    List<ResortReservation> findByResortId(long resortId);
}
