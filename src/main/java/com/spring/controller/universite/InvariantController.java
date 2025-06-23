/*package com.spring.controller.universite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Filiere_universite;
import com.spring.service.Filiere_universiteService;
import com.spring.service.Matiere_semestre_par_filiereService;
import com.spring.service.SemestreService;
import com.spring.service.TokenService;
import com.spring.utility.Response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/invariant")
@CrossOrigin("*")
public class InvariantController {

    @Autowired
    private TokenService serviceToken;

    @Autowired
    private Filiere_universiteService serviceFiliere;

    @Autowired
    private SemestreService servicesemestre;

    @Autowired
    private Matiere_semestre_par_filiereService serviceMatiere;

    // ------------------  Filiere   -----------------------------

    @GetMapping("/filiere")
    public ResponseEntity<Response> ListeFiliere(@RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            response.setData(serviceFiliere.read());
            response.setMessage("Réussi");
            // System.out.println("Tonga");
            // response.setData(serviceFiliere.read());
            response.setStatus_code("200");
            return new ResponseEntity<>(response , HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping("/filiere/{id}")
    public ResponseEntity<Response> getOneFiliere(@RequestHeader("Authorization") String authorizationHeader , @PathVariable Long id) {
        Response response = new Response();
        try {
            Filiere_universite filiere = serviceFiliere.readById(id);
            Long countMatiere = serviceMatiere.CountMatiereByFiliere(id);
            Map<String , Object> data = new HashMap();
            data.put("Filiere", filiere);
            data.put("countMatiere", countMatiere);
            response.setData(data);
            response.setMessage("Réussi");
            response.setStatus_code("200");
            return new ResponseEntity<>(response , HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }
}*/