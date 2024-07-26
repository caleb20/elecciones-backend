package com.caleb.elecciones.repository;

import com.caleb.elecciones.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    Voto findByCodigoAlumno(Long codigoAlumno);
}
