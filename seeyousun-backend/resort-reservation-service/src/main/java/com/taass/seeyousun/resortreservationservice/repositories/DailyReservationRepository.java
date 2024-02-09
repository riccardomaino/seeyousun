package com.taass.seeyousun.resortreservationservice.repositories;

import com.taass.seeyousun.resortreservationservice.model.DailyReservation;
import com.taass.seeyousun.resortreservationservice.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyReservationRepository extends JpaRepository<DailyReservation,Long> {

    List<DailyReservation> findDistinctByDateBetweenAndResortId(LocalDate initialDate, LocalDate finalDate, Long resortId);

    @Query("SELECT r " +
            "FROM DailyReservation d JOIN d.reservations r WHERE d.resortId = :resortId AND d.date = :date")
    List<Reservation> findReservedPlaceOfResortInDate(long resortId, LocalDate date);

    @Query("SELECT rr,r FROM DailyReservation rr join rr.reservations r WHERE r.userId = :userId")
    List<DailyReservation> findByUser(Long userId);
}
