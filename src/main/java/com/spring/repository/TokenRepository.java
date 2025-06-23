package com.spring.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.Token;


public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);

    @Transactional
    void deleteByToken(String token);

    Optional<Token> findByTokenAndDateExpirationBefore(String token, Date date);

    Token findByTokenAndDateExpirationAfter(String token, Date date);
}
