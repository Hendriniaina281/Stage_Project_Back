package com.spring.controller;

import com.spring.model.Verifemail;
import com.spring.service.VerifemailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Verifemail")
@CrossOrigin("*")
public class VerifemailController {

    @Autowired
    private VerifemailService services;

    @GetMapping("/findOne")
    public ResponseEntity<List<Verifemail>> findEmailAndToken(
            @RequestParam String email1,
            @RequestParam String token1) {
        try {
            List<Verifemail> result = services.findOne(email1, token1);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/findEmailAndToken")
    public ResponseEntity<List<Verifemail>> findEmailAndToken(
            @RequestParam String email1,
            @RequestParam String email2,
            @RequestParam String token1,
            @RequestParam String token2) {
        try {
            List<Verifemail> results = services.tokenEmail(email1, email2, token1, token2);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Verifemail object) {
        try {
            services.create(object);
            return ResponseEntity.ok("Créé avec succès");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de la création: " + e.getMessage());
        }
    }

    @GetMapping("/find")
    public ResponseEntity<List<Verifemail>> getAll() {
        try {
            List<Verifemail> results = services.read();
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Verifemail> getById(@PathVariable int id) {
        try {
            Optional<Verifemail> optional = services.readById(id);
            return optional.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(404).body(null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Verifemail> update(@PathVariable int id, @RequestBody Verifemail object) {
        try {
            Verifemail updated = services.update(id, object);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            services.delete(id);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Il y a une erreur: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAll() {
        try {
            services.deleteAll();
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Il y a une erreur: " + e.getMessage());
        }
    }
}
