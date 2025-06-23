package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spring.service.EmailVerificationService;

@RestController
@RequestMapping("/Registration")
@CrossOrigin("*")

public class RegistrationController {

    @Autowired
    private EmailVerificationService emailVerificationService;

    @PostMapping("/refus")
    public ResponseEntity<String> sendEmailRefus(@RequestParam String email,@RequestParam String ecole) {
        String verify = emailVerificationService.envoyerRefus(email,ecole);
        return ResponseEntity.ok(verify);
    }

    @PostMapping("/academyprof")
    public ResponseEntity<String> accept(@RequestParam String email,@RequestParam String ecole,@RequestParam String nomprenom) {
        String verify = emailVerificationService.academyProf(email,ecole,nomprenom);
        return ResponseEntity.ok(verify);
    }

    @PostMapping("/register")
    public ResponseEntity<String> startRegistration(@RequestParam String email) {
        String verify = emailVerificationService.sendVerificationEmail(email);
        return ResponseEntity.ok(verify);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        boolean isVerified = emailVerificationService.verifyEmailToken(token);
        if (isVerified) {
            return ResponseEntity.ok("Votre e-mail a été vérifié avec succès ! Vous pouvez maintenant finaliser votre inscription.");
        } else {
            return ResponseEntity.badRequest().body("Jeton invalide ou expiré.");
        }
    }
}

