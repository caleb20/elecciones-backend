package com.caleb.elecciones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Rol implements GrantedAuthority {
    @Id
    private Long id;
    private String nombre;

    @Override
    public String getAuthority() {
        return nombre;
    }

//    @ManyToMany(mappedBy = "rol")
//    private Set<Usuario> usuarios = new HashSet<>();
}
