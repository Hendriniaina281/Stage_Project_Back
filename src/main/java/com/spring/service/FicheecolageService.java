 package com.spring.service;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import com.spring.model.Ficheecolage;
 import com.spring.repository.FicheecolageRepository;
 import java.util.List;
 import java.util.Optional;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Service
 public class FicheecolageService {
    private final FicheecolageRepository repo;

    public Double sommeTotal() {
      return repo.sommeTotal();
   }

    public Double sommeEcolage(int idanneescolaire) {
      return repo.sumEcolage(idanneescolaire);
   }


    public Double somme(int idetudiant) {
      return repo.sum(idetudiant);
   }


    
    public List<Ficheecolage> getByEtudiant(int idetudiant) {
      return repo.findByIdetudiant(idetudiant);
   }

    @Autowired
    public FicheecolageService(FicheecolageRepository rep) {
        this.repo = rep;
    }

    public Ficheecolage create(Ficheecolage object) {
       return repo.save(object);
   }


    public List<Ficheecolage> read() {
       return repo.findAll();
    }

    public Optional<Ficheecolage> readById(int id) {
       return repo.findById(id);
    }

    public Ficheecolage update(int id, Ficheecolage object) {
       try {

           Optional<Ficheecolage> optional = repo.findById(id);
           if (optional.isPresent()) {
               Ficheecolage existing = optional.get();
               existing = (Ficheecolage) com.spring.utility.Utilitaire.addValueObject(existing,object);
               return repo.save(existing);
           }
       return null;
       }catch(Exception ex){
           ex.printStackTrace();
           return null;
       }
    }

    public void delete(int id) {
       repo.deleteById(id);
    }



}

