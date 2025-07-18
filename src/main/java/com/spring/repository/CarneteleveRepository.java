 package com.spring.repository;

 import java.util.List;
 
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

 import com.spring.model.Carneteleve;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Repository
 public interface CarneteleveRepository extends JpaRepository<Carneteleve, Integer>  {
   List<Carneteleve> findByIdetudiant(int idetudiant);
}

