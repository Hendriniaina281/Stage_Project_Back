package com.spring.controller;

import com.spring.model.Academyusers;
import com.spring.model.Offreecole;
import com.spring.model.Professeur;
import com.spring.model.Users;
import com.spring.model.V_demandeprof;
import com.spring.service.AcademyusersService;
import com.spring.service.OffreecoleService;
import com.spring.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
   File generated By
   Christooj_Generation_Code
*/
@RestController
@RequestMapping("/Offreecole")
@CrossOrigin("*")
public class OffreecoleController{
   @Autowired
   private OffreecoleService services;

   @Autowired
   TokenService servicetoken;

   @Autowired
   AcademyusersService academyUser;


   @GetMapping("/offreAcademy")
    public List<Offreecole> getOffreByAcademy(@RequestHeader("Authorization") String token) {
        try {
            Users user = servicetoken.getUser(token);
            Academyusers ac = academyUser.findAcademyId(user.getId());
            List<Offreecole> all = services.getOffreByIdEcole(ac.getAcademy_id());
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   @PostMapping("/create")
    public String create(@RequestBody Offreecole object) {
    try {
        services.create(object);
        return "Cr��e avec succ�s";
    } catch (Exception e) {
        e.printStackTrace();
        return "Erreur lors de la cr�ation: " + e.getMessage();
    }
}

   @GetMapping("/find")
    public List<Offreecole> getAll() {
        try {
            List<Offreecole> all = services.read();
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   @GetMapping("/find/{id}")
    public Offreecole getById(@PathVariable int id) {
        try {
            Optional<Offreecole> optional = services.readById(id);
            return  optional.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   @PutMapping("/update/{id}")
    public Offreecole update(@PathVariable int id, @RequestBody Offreecole object) {
        try {
            Offreecole updated = services.update(id,object);
            return updated;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        try {
            services.delete(id);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
           return "Il ya erreur: "+e.getMessage();
        }
    }


}

