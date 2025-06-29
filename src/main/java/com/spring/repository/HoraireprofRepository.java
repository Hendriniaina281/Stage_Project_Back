 package com.spring.repository;

 import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

 import com.spring.model.Horaireprof;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Repository
 public interface HoraireprofRepository extends JpaRepository<Horaireprof, Integer>  {
   List<Horaireprof> findByProfid(Integer profid);
}

