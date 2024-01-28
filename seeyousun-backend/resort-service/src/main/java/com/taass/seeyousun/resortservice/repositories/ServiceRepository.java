package com.taass.seeyousun.resortservice.repositories;

import com.taass.seeyousun.resortservice.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
