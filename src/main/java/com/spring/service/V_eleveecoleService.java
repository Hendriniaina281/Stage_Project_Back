 package com.spring.service;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import com.spring.model.V_eleveecole;
 import com.spring.repository.V_eleveecoleRepository;
 import java.util.List;
 import java.util.Optional;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Service
 public class V_eleveecoleService {
    private final V_eleveecoleRepository repo;

    public List<V_eleveecole> getEleveEcole(Integer idecole) {
      return repo.findByIdecole(idecole);
  }

    @Autowired
    public V_eleveecoleService(V_eleveecoleRepository rep) {
        this.repo = rep;
    }

    public V_eleveecole create(V_eleveecole object) {
       return repo.save(object);
   }


    public List<V_eleveecole> read() {
       return repo.findAll();
    }

    public Optional<V_eleveecole> readById(int id) {
       return repo.findById(id);
    }

    public V_eleveecole update(int id, V_eleveecole object) {
       try {

           Optional<V_eleveecole> optional = repo.findById(id);
           if (optional.isPresent()) {
               V_eleveecole existing = optional.get();
               existing = (V_eleveecole) com.spring.utility.Utilitaire.addValueObject(existing,object);
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

