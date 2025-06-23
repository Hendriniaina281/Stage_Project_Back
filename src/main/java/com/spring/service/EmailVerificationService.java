package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailVerificationService {

    @Autowired
    private EmailService emailService;
    private Map<String, String> pendingVerifications = new HashMap<>();


    public String envoyerRefus(String email,String ecole) {
        String subject = "Demande d'emplois chez:"+ecole;
        String message = "Nous tenons à vous informer que votre demande d'emplois chez "+ecole+" a été accepté.Désormais vous faites partie des professeurs de "+ecole;
        
        emailService.sendSimpleMessage(email, subject, message);

        return "oke";
    }

    public String academyProf(String email,String ecole,String nomprenom) {
        String subject = "Demande de confirmation chez:"+ecole;
        String message = nomprenom.trim()+" a accepté la demande envoyé. Désormais, il fait partie de votre école.";
        
        emailService.sendSimpleMessage(email, subject, message);

        return "oke";
    }


    public String sendVerificationEmail(String email) {
    Random random = new Random();
    int verificationCode = 1000 + random.nextInt(9000); 

    pendingVerifications.put(String.valueOf(verificationCode), email);
    String subject = "Confirmez votre adresse e-mail";
    String message = "Votre code de vérification est : " + verificationCode;

    emailService.sendSimpleMessage(email, subject, message);

    return String.valueOf(verificationCode);
}    

    public boolean verifyEmailToken(String token) {
        if (pendingVerifications.containsKey(token)) {
            String email = pendingVerifications.get(token);
            pendingVerifications.remove(token);

            return true;
        }
        return false;
    }
}
