 package com.spring.service;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import com.spring.model.V_demandeajoutprof;
 import com.spring.repository.V_demandeajoutprofRepository;
 import java.util.List;
 import java.util.Optional;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Service
 public class V_demandeajoutprofService {
    private final V_demandeajoutprofRepository repo;

    public List<V_demandeajoutprof> getDemande(int idprof) {
      return repo.getDemandeAjoutProf(idprof);
   }

    @Autowired
    public V_demandeajoutprofService(V_demandeajoutprofRepository rep) {
        this.repo = rep;
    }

    public V_demandeajoutprof create(V_demandeajoutprof object) {
       return repo.save(object);
   }


    public List<V_demandeajoutprof> read() {
       return repo.findAll();
    }

    public Optional<V_demandeajoutprof> readById(int id) {
       return repo.findById(id);
    }

    public V_demandeajoutprof update(int id, V_demandeajoutprof object) {
       try {

           Optional<V_demandeajoutprof> optional = repo.findById(id);
           if (optional.isPresent()) {
               V_demandeajoutprof existing = optional.get();
               existing = (V_demandeajoutprof) com.spring.utility.Utilitaire.addValueObject(existing,object);
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

