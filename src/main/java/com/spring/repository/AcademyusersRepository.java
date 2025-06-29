 package com.spring.repository;

 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

 import com.spring.model.Academyusers;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Repository
 public interface AcademyusersRepository extends JpaRepository<Academyusers, Integer>  {

   @Query(value = "SELECT COUNT(id) > 0 FROM academyusers  WHERE users_id = :usersId" , nativeQuery = true)
   boolean existsByUsersId(Integer usersId);

   @Query(value = "SELECT academy_id  FROM academyusers  WHERE users_id = :user_id" , nativeQuery = true)
   int findByAcademy(int user_id);
   
    Academyusers findByUsers(Integer users_id);

}

