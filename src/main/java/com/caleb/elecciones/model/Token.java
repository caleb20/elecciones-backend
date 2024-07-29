package com.caleb.elecciones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Token {
    @Id
    private String token;
    private String correo;
    private Boolean isExpired;
    private Boolean isRevoked;
}
