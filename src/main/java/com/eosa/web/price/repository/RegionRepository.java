package com.eosa.web.price.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eosa.web.price.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    
}
