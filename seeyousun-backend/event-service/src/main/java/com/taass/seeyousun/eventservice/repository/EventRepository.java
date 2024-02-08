package com.taass.seeyousun.eventservice.repository;

import com.taass.seeyousun.eventservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByResortId(Long resortId);

    @Query("FROM Event e WHERE :userId MEMBER OF e.userSubscribed")
    List<Event> findByUSerId(Long userId);
}
