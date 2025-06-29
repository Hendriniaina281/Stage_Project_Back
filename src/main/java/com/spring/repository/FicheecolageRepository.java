 package com.spring.repository;

 import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

 import com.spring.model.Ficheecolage;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Repository
 public interface FicheecolageRepository extends JpaRepository<Ficheecolage, Integer>  {
  

   @Query(value = "select * from ficheEcolage where idetudiant = :idetudiant order by id", nativeQuery = true)
   List<Ficheecolage> findByIdetudiant(@Param("idetudiant") int idetudiant);

   @Query(value = "select sum(frais) as somme from ficheEcolage where statut = 1 and idetudiant = :idetudiant", nativeQuery = true)
   Double sum(@Param("idetudiant") int idetudiant);

   @Query(value = "select sum(frais) as somme from ficheecolage join etudiantecole on ficheecolage.idetudiant = etudiantecole.id where idanneescolaire =:idanneescolaire ", nativeQuery = true)
   Double sumEcolage(@Param("idanneescolaire") int idanneescolaire);

   @Query(value = "select sum(fraisdescolarite.frais) from ficheecolage "+
   "join etudiantecole on ficheecolage.idetudiant = etudiantecole.id "+
   "join fraisdescolarite on etudiantecole.idecoleclasse = fraisdescolarite.idecoleclasse ", nativeQuery = true)
   Double sommeTotal();
}

