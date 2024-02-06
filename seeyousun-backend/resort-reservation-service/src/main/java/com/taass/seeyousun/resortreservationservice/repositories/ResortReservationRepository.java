package com.taass.seeyousun.resortreservationservice.repositories;

import com.taass.seeyousun.resortreservationservice.model.DailyReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ResortReservationRepository extends JpaRepository<DailyReservation,Long> {
    List<DailyReservation> findByResortIdAndDate(long resortId, LocalDate date);

    List<DailyReservation> findByResortId(long resortId);
}
