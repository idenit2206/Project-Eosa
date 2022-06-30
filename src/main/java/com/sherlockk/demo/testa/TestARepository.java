package com.sherlockk.demo.testa;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TestARepository extends JpaRepository<TestA, Long> {
    
    //#{#Param.컬럼명}
    //#{#Param.복합키명.컬럼명}
    @Modifying
    @Transactional
    @Query(value="INSERT INTO TestA(columnA) VALUES(:#{#TestA.columnA})", nativeQuery = true)
    public int customAdd(@Param("TestA") TestA entity);

}
