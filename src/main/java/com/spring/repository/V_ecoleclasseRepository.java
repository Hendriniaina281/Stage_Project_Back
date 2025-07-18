 package com.spring.repository;

 import java.util.List;
 
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

 import com.spring.model.V_ecoleclasse;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Repository
 public interface V_ecoleclasseRepository extends JpaRepository<V_ecoleclasse, Integer>  {
   List<V_ecoleclasse> findByIduser(int iduser);
   List<V_ecoleclasse> findByEcoleclasseid(Integer ecoleclasseid);

}

