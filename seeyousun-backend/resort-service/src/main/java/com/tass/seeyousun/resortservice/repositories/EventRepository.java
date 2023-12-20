package com.tass.seeyousun.resortservice.repositories;

import com.tass.seeyousun.resortservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
