 package com.spring.service;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import com.spring.model.Salaireprof;
 import com.spring.repository.SalaireprofRepository;
 import java.util.List;
 import java.util.Optional;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Service
 public class SalaireprofService {
    private final SalaireprofRepository repo;

    @Autowired
    public SalaireprofService(SalaireprofRepository rep) {
        this.repo = rep;
    }

    public Salaireprof create(Salaireprof object) {
       return repo.save(object);
   }


    public List<Salaireprof> read() {
       return repo.findAll();
    }

    public Optional<Salaireprof> readById(int id) {
       return repo.findById(id);
    }

    public Salaireprof update(int id, Salaireprof object) {
       try {

           Optional<Salaireprof> optional = repo.findById(id);
           if (optional.isPresent()) {
               Salaireprof existing = optional.get();
               existing = (Salaireprof) com.spring.utility.Utilitaire.addValueObject(existing,object);
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

