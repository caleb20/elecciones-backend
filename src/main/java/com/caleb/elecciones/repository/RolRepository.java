package com.caleb.elecciones.repository;

import com.caleb.elecciones.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
