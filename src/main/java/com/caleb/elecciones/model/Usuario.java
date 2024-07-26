package com.caleb.elecciones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Usuario {
    @Id
    private Long codigo;
    private String correo;
    private String password;
}
