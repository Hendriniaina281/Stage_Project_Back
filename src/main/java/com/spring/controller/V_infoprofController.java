package com.spring.controller;

import com.spring.model.Academyusers;
import com.spring.model.Users;
import com.spring.model.V_infoprof;
import com.spring.service.AcademyusersService;
import com.spring.service.TokenService;
import com.spring.service.V_infoprofService;

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
@RequestMapping("/V_infoprof")
@CrossOrigin("*")
public class V_infoprofController{
   @Autowired
   private V_infoprofService services;

   @Autowired
    TokenService servicetoken;

    
   @Autowired
   private AcademyusersService serviceAc;

   @GetMapping("/findInfoProf")
    public List<V_infoprof> getInfoprof(@RequestHeader("Authorization") String token) {
        try {
            Users user = servicetoken.getUser(token);
            Academyusers ac =  serviceAc.findAcademyId(user.getId());
            List<V_infoprof> all = services.readAcademyProf(ac.getAcademy_id());
            
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   @PostMapping("/create")
    public String create(@RequestBody V_infoprof object) {
    try {
        services.create(object);
        return "Cr��e avec succ�s";
    } catch (Exception e) {
        e.printStackTrace();
        return "Erreur lors de la cr�ation: " + e.getMessage();
    }
}

   @GetMapping("/find")
    public List<V_infoprof> getAll() {
        try {
            List<V_infoprof> all = services.read();
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   @GetMapping("/find/{id}")
    public V_infoprof getById(@PathVariable int id) {
        try {
            Optional<V_infoprof> optional = services.readById(id);
            return  optional.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   @PutMapping("/update/{id}")
    public V_infoprof update(@PathVariable int id, @RequestBody V_infoprof object) {
        try {
            V_infoprof updated = services.update(id,object);
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

