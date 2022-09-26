package com.eosa.web.price.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eosa.web.price.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
