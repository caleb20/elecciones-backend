package com.caleb.elecciones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PartidoPolitico {
    @Id
    private Integer codigo;
    private String nombrePartido;
    private String simbolo;
}
