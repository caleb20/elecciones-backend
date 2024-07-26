package com.caleb.elecciones.service.alumno;

import com.caleb.elecciones.model.Alumno;

import java.util.List;

public interface AlumnoService {
    Alumno guardar(Alumno alumno);
    List<Alumno> listar();
    Alumno buscar(Long id);
    Alumno actualizar(Alumno alumno);
    void eliminar(Long id);
}
