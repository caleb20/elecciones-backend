package com.caleb.elecciones.service.partidopolitico;

import com.caleb.elecciones.model.PartidoPolitico;

import java.util.List;

public interface PartidoPoliticoService {
    PartidoPolitico guardar(PartidoPolitico partidoPolitico);
    List<PartidoPolitico> listar();
    PartidoPolitico buscar(Integer codigo);
    PartidoPolitico actualizar(PartidoPolitico alumno);
    void eliminar(Integer codigo);
}
