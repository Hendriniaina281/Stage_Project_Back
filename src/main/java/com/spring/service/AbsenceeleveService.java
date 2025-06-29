 package com.spring.service;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import com.spring.model.Absenceeleve;
 import com.spring.repository.AbsenceeleveRepository;
 import java.util.List;
 import java.util.Optional;

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Service
 public class AbsenceeleveService {
    private final AbsenceeleveRepository repo;

    public List<Absenceeleve> getAbsence(Integer idetudiant) {
      return repo.getAbsenceeleve(idetudiant);
   }

    @Autowired
    public AbsenceeleveService(AbsenceeleveRepository rep) {
        this.repo = rep;
    }

    public Absenceeleve create(Absenceeleve object) {
       return repo.save(object);
   }


    public List<Absenceeleve> read() {
       return repo.findAll();
    }

    public Optional<Absenceeleve> readById(int id) {
       return repo.findById(id);
    }

    public Absenceeleve update(int id, Absenceeleve object) {
       try {

           Optional<Absenceeleve> optional = repo.findById(id);
           if (optional.isPresent()) {
               Absenceeleve existing = optional.get();
               existing = (Absenceeleve) com.spring.utility.Utilitaire.addValueObject(existing,object);
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

