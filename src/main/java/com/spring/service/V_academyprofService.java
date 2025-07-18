 package com.spring.service;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import com.spring.model.V_academyprof;
 import com.spring.repository.V_academyprofRepository;
 import java.util.List;
 import java.util.Optional;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Service
 public class V_academyprofService {
    private final V_academyprofRepository repo;

    @Autowired
    public V_academyprofService(V_academyprofRepository rep) {
        this.repo = rep;
    }

    public V_academyprof create(V_academyprof object) {
       return repo.save(object);
   }

   public List<V_academyprof> findByAcademyId(int academyid) {
      return repo.findByAcademyid(academyid);
   }


    public List<V_academyprof> read() {
       return repo.findAll();
    }

    public Optional<V_academyprof> readById(int id) {
       return repo.findById(id);
    }

    public V_academyprof update(int id, V_academyprof object) {
       try {

           Optional<V_academyprof> optional = repo.findById(id);
           if (optional.isPresent()) {
               V_academyprof existing = optional.get();
               existing = (V_academyprof) com.spring.utility.Utilitaire.addValueObject(existing,object);
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

