package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.spring.model.Verifemail;

@Repository
public interface VerifemailRepository extends JpaRepository<Verifemail, Integer> {

   @Query(value = "SELECT * FROM verifEmail WHERE (email = :email1 AND token = :token1) OR (email = :email2 AND token = :token2)", nativeQuery = true)
   List<Verifemail> findVerifEmails(
       @Param("email1") String email1, 
       @Param("token1") String token1, 
       @Param("email2") String email2, 
       @Param("token2") String token2
   );

   @Query(value = "SELECT * FROM verifEmail WHERE (email = :email1 AND token = :token1)", nativeQuery = true)
   List<Verifemail> findOne(
       @Param("email1") String email1, 
       @Param("token1") String token1
   );
}
