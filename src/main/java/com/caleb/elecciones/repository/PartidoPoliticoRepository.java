package com.caleb.elecciones.repository;

import com.caleb.elecciones.model.PartidoPolitico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoPoliticoRepository extends JpaRepository<PartidoPolitico, Integer> {
}
