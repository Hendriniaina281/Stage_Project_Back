 package com.spring.repository;

 import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

 import com.spring.model.V_academyannee;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Repository
 public interface V_academyanneeRepository extends JpaRepository<V_academyannee, Integer>  {
   List<V_academyannee> findByProfid(int profid);
}

