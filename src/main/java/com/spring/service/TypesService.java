 package com.spring.service;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import com.spring.model.Types;
 import com.spring.repository.TypesRepository;
 import java.util.List;
 import java.util.Optional;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Service
 public class TypesService {
    private final TypesRepository repo;

    @Autowired
    public TypesService(TypesRepository rep) {
        this.repo = rep;
    }

    public Types create(Types object) {
       return repo.save(object);
   }


    public List<Types> read() {
       return repo.findAll();
    }

    public Optional<Types> readById(int id) {
       return repo.findById(id);
    }

    public Types update(int id, Types object) {
       try {

           Optional<Types> optional = repo.findById(id);
           if (optional.isPresent()) {
               Types existing = optional.get();
               existing = (Types) com.spring.utility.Utilitaire.addValueObject(existing,object);
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

