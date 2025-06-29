package com.spring.controller;

import com.spring.model.Ficheecolage;
import com.spring.service.FicheecolageService;

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
@RequestMapping("/Ficheecolage")
@CrossOrigin("*")
public class FicheecolageController{
   @Autowired
   private FicheecolageService services;

   @GetMapping("/sommeTotal")
   public Double sommeTotal() {
       try {
           Double all = services.sommeTotal();
           return all;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }

   @GetMapping("/sommeEcolage/{idanneescolaire}")
   public Double sommeEcolage(@PathVariable int idanneescolaire) {
       try {
           Double all = services.sommeEcolage(idanneescolaire);
           return all;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }


   @GetMapping("/somme/{idetudiant}")
    public Double sommeFrais(@PathVariable int idetudiant) {
        try {
            Double all = services.somme(idetudiant);
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


   @GetMapping("/findEcolage/{idetudiant}")
    public List<Ficheecolage> getEcolage(@PathVariable int idetudiant) {
        try {
            List<Ficheecolage> all = services.getByEtudiant(idetudiant);
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


   @PostMapping("/create")
    public String create(@RequestBody Ficheecolage object) {
    try {
        services.create(object);
        return "Cr��e avec succ�s";
    } catch (Exception e) {
        e.printStackTrace();
        return "Erreur lors de la cr�ation: " + e.getMessage();
    }
}

   @GetMapping("/find")
    public List<Ficheecolage> getAll() {
        try {
            List<Ficheecolage> all = services.read();
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   @GetMapping("/find/{id}")
    public Ficheecolage getById(@PathVariable int id) {
        try {
            Optional<Ficheecolage> optional = services.readById(id);
            return  optional.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   @PutMapping("/update/{id}")
    public Ficheecolage update(@PathVariable int id, @RequestBody Ficheecolage object) {
        try {
            Ficheecolage updated = services.update(id,object);
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

