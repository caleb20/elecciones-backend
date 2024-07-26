package com.caleb.elecciones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Alumno {
    @Id
    private Long codigo;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Long codigoUsuario;
}
