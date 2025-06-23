package com.spring.service;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.spring.config.JWToken;
import com.spring.model.Academy;
import com.spring.model.Academyusers;
import com.spring.model.Profil;
import com.spring.model.Token;
import com.spring.model.Users;
import com.spring.repository.TokenRepository;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.LinkedHashMap;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    private ProfilService serviceprofil ;

    @Autowired
    private AcademyService serviceacademy ;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    // Méthode pour enregistrer un nouveau token
    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }

    @Transactional
    public void deleteTokenByToken(String token) {
        tokenRepository.deleteByToken(token);
    }


    public Optional<Token> findTokenByConditions(String token, Date currentDate) {
        return tokenRepository.findByTokenAndDateExpirationBefore(token, currentDate);
    }

    public Token findByToken(String token) {
        Optional<Token> optionalToken = tokenRepository.findByToken(token);
        return optionalToken.orElse(null);
    }

    public Claims getClaims(Token token) {
        try {
            JWToken jwttoken = new JWToken();
            return jwttoken.extractClaims(token);
        } catch (Exception e) {
            deleteTokenByToken(token.getToken());
            throw e;
        }
    }

    public String checkAuthorizationBearer(String authorizationHeader) throws Exception {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new Exception("error");
        }
        return authorizationHeader.substring(7);
    }

    public Claims validationToken(String authorizationHeader) throws Exception {
        String token = checkAuthorizationBearer(authorizationHeader);
        Token tk = findByToken(token);
        if (tk == null) {
            throw new Exception("Veuillez vous connecter. ");
        }
        tk.setToken(token);
        return getClaims(tk);
    }

    public void checkRole(String authorizationHeader, int idprofile) throws Exception {
        Claims claims = validationToken(authorizationHeader);
        if (!claims.get("role").equals(idprofile)) {
            throw new Exception("Accès interdit. Vous n'avez pas le droit d'accéder à cette ressource");
        }
    }

    public Claims getClaims(String authorizationHeader) throws Exception {
        return validationToken(authorizationHeader);
    }

    public void checkSansRole(String authorizationHeader) throws Exception {
        validationToken(authorizationHeader);
    }

    public void save(){
        Token token = new Token();
        token.setToken("sampleToken");
        token.setCle("sampleKey");
        token.setDateCreation(new Date());
        token.setDateExpiration(new Date(System.currentTimeMillis() + 86400000)); // 1 jour

        Token savedToken = saveToken(token);
        System.out.println("Token sauvegardé : " + savedToken);
    }

    private Claims getClaims(String token , String SECRET_KEY) {
        try {
            return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Token non valide ou expirer");
        }
    }

    public Users getUser(String tok) throws Exception {
        try {
            String token = tok.replace("Bearer ", "");
            String  SECRET_KEY = "";
            Token t = this.findByToken(token);

            if(t == null) {
                throw new Exception("Ce token n'existe pas! ");
            }
            SECRET_KEY = t.getCle();
            Claims l = this.getClaims(token ,SECRET_KEY);
            Users u = new Users();
            u.setId(Integer.parseInt(l.get("id").toString()));
            u.setNom((String) l.get("nom"));
            u.setPrenom((String) l.get("prenom"));
            u.setDatenaissance(new java.util.Date((Long) l.get("datenaissance")));
            u.setAdminnom((String) l.get("adminNom"));
            u.setEmail((String) l.get("email"));
            u.setMdp((String) l.get("mdp"));
            u.setAdresse((String) l.get("adresse"));
            u.setAcademys((List<Academy>) l.get("academy"));
            u.setAcademyuserss((List<Academyusers>) l.get("adminusers"));
            u.setProfil_id(Integer.parseInt(l.get("profil_id").toString()));
            return u;
        } catch (Exception e) {
            throw e;
        }
    }

    public Long getUsers_id(String tok) throws Exception {
        try {
            String token = tok.replace("Bearer ", "");
            String  SECRET_KEY = "";
            Token t = this.findByToken(token);
            if(t == null) {
                throw new Exception("Ce token n'existe pas! ");
            }
            SECRET_KEY = t.getCle();
            Claims l = this.getClaims(token ,SECRET_KEY);
            return Long.parseLong(l.get("id").toString());
        } catch (Exception e) {
            throw e;
        }
    }

    // public Academy getAcademy(String tok) throws Exception {
    //     try {
    //         String token = tok.replace("Bearer ", "");
    //         String  SECRET_KEY = "";
    //         Token t = this.findByToken(token);
    //         if(t == null) {
    //             throw new Exception("Ce token n'existe pas! ");
    //         }
    //         SECRET_KEY = t.getCle();
    //         Claims l = this.getClaims(token ,SECRET_KEY);
    //         List<Academyusers> AcademyUsers = (List<Academyusers>) l.get("adminusers");
    //         if(AcademyUsers.isEmpty()){
    //             throw new Exception("L academy que vous appele n ' existe pas");
    //         }
    //         Academy a = serviceacademy.readById(AcademyUsers.get(0).getAcademy_id()).get();
    //         return a;
    //     } catch (Exception e) {
    //         throw e;
    //     }
    // }

    

    public Academy getAcademy(String tok) throws Exception {
        try {
            String token = tok.replace("Bearer ", "");
            String SECRET_KEY = "";
            Token t = this.findByToken(token);
            if (t == null) {
                throw new Exception("Ce token n'existe pas! ");
            }
            SECRET_KEY = t.getCle();
            Claims l = this.getClaims(token, SECRET_KEY);
            
            // Get the list of LinkedHashMap objects
            List<LinkedHashMap<String, Object>> rawAcademyUsers = (List<LinkedHashMap<String, Object>>) l.get("adminusers");
            if (rawAcademyUsers.isEmpty()) {
                throw new Exception("L'academy que vous appelez n'existe pas");
            }
            
            // Convert LinkedHashMap to List<Academyusers>
            List<Academyusers> AcademyUsers = new ArrayList<>();
            for (LinkedHashMap<String, Object> rawUser : rawAcademyUsers) {
                Academyusers user = new Academyusers();
                user.setAcademy_id((Integer) rawUser.get("academy_id")); // assuming academy_id is a Long
                // set other fields similarly
                AcademyUsers.add(user);
            }

            Academy a = serviceacademy.readById(AcademyUsers.get(0).getAcademy_id()).get();
            return a;
        } catch (Exception e) {
            throw e;
        }
    }


}