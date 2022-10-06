package com.eosa.web.price.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eosa.web.price.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value="SELECT * FROM Category c ORDER BY c.categoryIdx ASC", nativeQuery = true)
    List<Category> findAllASC();
    
}
