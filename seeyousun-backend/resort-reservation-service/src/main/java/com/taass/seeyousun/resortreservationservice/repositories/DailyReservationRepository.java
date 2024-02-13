package com.taass.seeyousun.resortreservationservice.repositories;

import com.taass.seeyousun.resortreservationservice.dto.ReservationFullDTO;
import com.taass.seeyousun.resortreservationservice.dto.UmbrellaDTO;
import com.taass.seeyousun.resortreservationservice.model.DailyReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyReservationRepository extends JpaRepository<DailyReservation,Long> {

    List<DailyReservation> findDistinctByDateBetweenAndResortId(LocalDate initialDate, LocalDate finalDate, Long resortId);

    @Query("SELECT new com.taass.seeyousun.resortreservationservice.dto.UmbrellaDTO(r.reservedUmbrellaLine,r.reservedUmbrellaColumn,r.persistenceTypeEnum) " +
            "FROM DailyReservation d JOIN d.reservations r WHERE d.resortId = :resortId AND d.date = :date")
    List<UmbrellaDTO> findReservedPlaceOfResortInDate(Long resortId, LocalDate date);


    @Query("SELECT new com.taass.seeyousun.resortreservationservice.dto.ReservationFullDTO(d.resortId,d.date,r.numberOfSunbeds, " +
            "r.persistenceTypeEnum,r.reservedUmbrellaLine,r.reservedUmbrellaColumn,r.userUid,r.id,d.id) " +
            "FROM DailyReservation d join d.reservations r WHERE r.userUid = :userUid")
    List<ReservationFullDTO> findByUser(String userUid);
}
