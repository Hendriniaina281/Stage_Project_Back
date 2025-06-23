package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


import com.spring.config.JWToken;
import com.spring.model.Token;
import com.spring.model.Users;
import com.spring.service.AcademyService;
import com.spring.service.ProfilService;
import com.spring.service.TokenService;
import com.spring.service.UsersService;
import com.spring.utility.Response;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthentificationController {
    @Autowired
    UsersService serviceusers;

    @Autowired
    TokenService servicetoken;

    @Autowired
    ProfilService serviceprofil;

    @Autowired
    AcademyService serviceAcademy;
    
    @PostMapping("/login1")
    public ResponseEntity<Response> login1(@RequestParam("email") String email, @RequestParam("mdp") String mdp) {
        Response response = new Response();

        try {
            Users utilisateur = serviceusers.verificationLogin(email, mdp);

            if (utilisateur != null) {
                JWToken j = new JWToken(serviceusers);
                Map<String, Object> res = j.generateToken(utilisateur);

                Token token = new Token();
                token.setCle((String) res.get("cle"));
                token.setToken((String) res.get("token"));
                token.setDateCreation((Date) res.get("date"));
                token.setDateExpiration((Date) res.get("expirer"));

                // Assurez-vous que token n'est pas null
                if (token.getToken() != null && token.getCle() != null &&
                    token.getDateCreation() != null && token.getDateExpiration() != null) {
                    servicetoken.saveToken(token);
                    
                    response.setData(token); // Affichez uniquement le token dans la réponse
                    response.setStatus(HttpStatus.OK);
                    response.setStatus_code("200");
                    response.setMessage("Login réussi");
                } else {
                    response.setMessage("Erreur de génération du token");
                    response.setStatus_code("500");
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                response.setMessage("Email ou mot de passe incorrect");
                response.setStatus_code("401");
                response.setStatus(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            response.setMessage("Erreur lors de la connexion: " + e.getMessage());
            response.setStatus_code("500");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, response.getStatus());
    }
        

   
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestParam("email") String email, @RequestParam("mdp") String mdp) {
        Response response = new Response();

        try {
            Users utilisateur = serviceusers.verificationLogin(email, mdp);
            
            if (utilisateur != null) {
                JWToken j = new JWToken(serviceusers);
                Map<String, Object> res = j.generateToken(utilisateur);

                Token token = new Token();
                token.setCle((String) res.get("cle"));
                token.setToken((String) res.get("token"));
                token.setDateCreation((Date) res.get("date"));
                token.setDateExpiration((Date) res.get("expirer"));

                // Assurez-vous que token n'est pas null
                if (token.getToken() != null && token.getCle() != null &&
                    token.getDateCreation() != null && token.getDateExpiration() != null) {
                    servicetoken.saveToken(token);
                    // Users u = servicetoken.getUser(token.getToken());
                    Map<String , Object> data = new HashMap();
                    data.put("token", token);
                    data.put("user", utilisateur);
                    response.setData(data);
                    // response.setMultidata(data);
                    response.setStatus(HttpStatus.OK);
                    response.setStatus_code("200");
                    response.setMessage("Login réussi");
                } else {
                    response.setMessage("Erreur de génération du token");
                    response.setStatus_code("500");
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                response.setStatus(HttpStatus.OK);
                response.setStatus_code("200");
                response.setMessage("Login réussi");
            } else {
                response.setMessage("Email ou mot de passe incorrect");
                response.setStatus_code("401");
                response.setStatus(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            response.setMessage("Erreur lors de la connexion: " + e.getMessage());
            response.setStatus_code("500");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, response.getStatus());
    }
        

    @GetMapping("/usersConnecte")
    public ResponseEntity<Response> getUsers(@RequestHeader("Authorization") String authorizationHeader) {
        Response response = new Response();
        try {
            servicetoken.checkRole(authorizationHeader, 2);
            Token t = servicetoken.findByToken(authorizationHeader);

            response.setMultidata(null);
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

    @GetMapping("/bonjour")
    public List<Users> sayHello() {
        
        return serviceusers.read();
    }


}
