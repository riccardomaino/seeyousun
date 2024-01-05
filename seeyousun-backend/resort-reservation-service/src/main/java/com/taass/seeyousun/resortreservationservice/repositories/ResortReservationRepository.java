package com.taass.seeyousun.resortreservationservice.repositories;

import com.taass.seeyousun.resortreservationservice.model.ResortReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResortReservationRepository extends JpaRepository<ResortReservation,Long> {
}
