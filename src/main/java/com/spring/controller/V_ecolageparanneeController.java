package com.spring.controller;

import com.spring.model.V_ecolageparannee;
import com.spring.service.V_ecolageparanneeService;

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
@RequestMapping("/V_ecolageparannee")
@CrossOrigin("*")
public class V_ecolageparanneeController{
   @Autowired
   private V_ecolageparanneeService services;


   @PostMapping("/create")
    public String create(@RequestBody V_ecolageparannee object) {
    try {
        services.create(object);
        return "Cr��e avec succ�s";
    } catch (Exception e) {
        e.printStackTrace();
        return "Erreur lors de la cr�ation: " + e.getMessage();
    }
}

   @GetMapping("/find")
    public List<V_ecolageparannee> getAll() {
        try {
            List<V_ecolageparannee> all = services.getV_ecolage();
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   @GetMapping("/find/{id}")
    public V_ecolageparannee getById(@PathVariable int id) {
        try {
            Optional<V_ecolageparannee> optional = services.readById(id);
            return  optional.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   @PutMapping("/update/{id}")
    public V_ecolageparannee update(@PathVariable int id, @RequestBody V_ecolageparannee object) {
        try {
            V_ecolageparannee updated = services.update(id,object);
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

