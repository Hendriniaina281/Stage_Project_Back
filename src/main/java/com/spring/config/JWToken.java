package com.spring.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.model.Token;
import com.spring.model.Users;
import com.spring.service.ProfilService;
import com.spring.service.UsersService;

import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class JWToken {
    private long JWT_EXPIRATION_MINUTES = 180;

    @Autowired
    private ProfilService serviceprofil;

    private UsersService serviceUser;

    @Autowired
    public JWToken(UsersService serviceUser) {
        this.serviceUser = serviceUser;
    }

    public JWToken() {
        this.serviceprofil = null;
        this.serviceUser = null;
    }

    private String verificationConnecte(int id) {
        if(id == 0){
            return "";
        }
        switch (id) {
            case 1:
                return "Ecole";
            case 2:
                return "Universite";
            case 3:
                return "Professeur";
            case 4:
                return "Parent";
            case 5:
                return "Eleve";
            default:
                return "";
        }
    }

    public Map<String, Object> generateToken(Users user) throws Exception {
        try {
            Date now = new Date();
            long jwtExpirationInMs = TimeUnit.MINUTES.toMillis(JWT_EXPIRATION_MINUTES);
            Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
            String cle = generateSecretKey();
    
            // Création des claims
            Claims claims = Jwts.claims().setSubject(Long.toString(user.getId()));
            claims.put("id", user.getId());
            claims.put("nom", user.getNom());
            claims.put("prenom", user.getPrenom());
            claims.put("datenaissance", user.getDatenaissance());
            claims.put("adminNom", user.getAdminnom());
            claims.put("email", user.getEmail());
            claims.put("adresse", user.getAdresse());
            claims.put("adminnom", user.getAdminnom());
            claims.put("academy", user.getAcademys());
            claims.put("adminusers", user.getAcademyuserss());
            claims.put("profil_id", user.getProfil_id());
    
            // Gestion du profil et récupération des informations d'utilisateur
            String profil = verificationConnecte(user.getId());
            Optional<Users> optionalUser = serviceUser.readById(user.getId());
            
            if (optionalUser.isPresent()) {
                Users us = optionalUser.get();
                claims.put("role", us.getProfil_id());
                claims.put(profil, true);
    
                String token = Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, cle)
                    .compact();
    
                // Affichage des détails du token
                // for (Map.Entry<String, Object> entry : claims.entrySet()) {
                //     System.out.println(entry.getKey() + ": " + entry.getValue());
                // }
    
                // Réponse avec les informations du token
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("cle", cle);
                response.put("date", now);
                response.put("expirer", expiryDate);
                response.put("profil_id", us.getProfil_id());
                
                return response;
            } else {
                throw new Exception("Aucun utilisateur trouvé avec l'ID fourni.");
            }
        } catch (Exception e) {
            throw new Exception("Erreur de génération du token: " + e.getMessage());
        }
    }
    
    public Claims extractClaims(Token token) {
        return Jwts.parser().setSigningKey(token.getCle()).parseClaimsJws(token.getToken()).getBody();
    }

    private static String generateSecretKey() {
        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    public boolean isTokenExpired(Token token) {
        Date expirationDate = extractClaims(token).getExpiration();
        return expirationDate.before(new Date());
    }
}