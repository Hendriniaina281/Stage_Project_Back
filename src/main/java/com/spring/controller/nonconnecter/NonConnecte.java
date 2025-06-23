/*package com.spring.controller.nonconnecter;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Academy;
import com.spring.service.AProposService;
import com.spring.service.AcademyService;
import com.spring.service.Filiere_universiteService;
import com.spring.service.NosServiceService;
import com.spring.service.PolitiqueService;
import com.spring.utility.Response;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping()
@CrossOrigin(origins = "*")
public class NonConnecte {

    @Autowired
    AcademyService serviceAcademy;
    
    @Autowired
    NosServiceService service;

    @Autowired
    AProposService serviceApropos;

    @Autowired
    PolitiqueService servicepolitique;

    @Autowired
    Filiere_universiteService serviceFiliere;

    @GetMapping("/academy")
    public ResponseEntity<Response> getListeAcademy() throws Exception  {
        Response response = new Response();
        try {
            
            response.setData(serviceAcademy.read());
            response.setStatus_code("200");
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/academy/{id}")
    public ResponseEntity<Response> getByIdAcadem (@PathVariable Long id) throws Exception  {
        Response response = new Response();
        try {
            Map<String , Object> liste = new HashMap<>();
            Academy academy = serviceAcademy.readById(id).get();
            liste.put("academy" , academy);
            liste.put("filiere", serviceFiliere.readByIdAcademy(academy.getId()));
            response.setData(liste);
            response.setStatus_code("200");
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    } 

    @GetMapping("/services")
    public ResponseEntity<Response> getService () throws Exception  {
        Response response = new Response();
        try {
            
            response.setData(service.findAll());
            System.out.println(service.findAll());
            response.setStatus_code("200");
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    } 

    @GetMapping("/propos")
    public ResponseEntity<Response> getApropos() throws Exception  {
        Response response = new Response();
        try {
            
            response.setData(serviceApropos.findAll());
            response.setStatus_code("200");
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    } 

    @GetMapping("/Politique")
    public ResponseEntity<Response> getPolitique() throws Exception  {
        Response response = new Response();
        try {
            
            response.setData(servicepolitique.findAll());
            response.setStatus_code("200");
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    } 
}*/

