 package com.spring.repository;

 import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

 import com.spring.model.V_classeinecole;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Repository
 public interface V_classeinecoleRepository extends JpaRepository<V_classeinecole, Integer>  {
   List<V_classeinecole> findByIdecole(Integer idecole);
}

