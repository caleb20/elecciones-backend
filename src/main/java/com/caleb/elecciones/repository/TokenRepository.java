package com.caleb.elecciones.repository;

import com.caleb.elecciones.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
    Optional<Token> findByToken(String token);
    List<Token> findAllByCorreo(String userEmail);
}