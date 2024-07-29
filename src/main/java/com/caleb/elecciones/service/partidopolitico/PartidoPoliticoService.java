package com.caleb.elecciones.service.partidopolitico;

import com.caleb.elecciones.model.PartidoPolitico;
import com.caleb.elecciones.response.GenericResponse;

import java.util.List;

public interface PartidoPoliticoService {
    PartidoPolitico guardar(PartidoPolitico partidoPolitico);
    GenericResponse<List<PartidoPolitico>> listar();
    PartidoPolitico buscar(Integer codigo);
    PartidoPolitico actualizar(PartidoPolitico alumno);
    void eliminar(Integer codigo);
}
