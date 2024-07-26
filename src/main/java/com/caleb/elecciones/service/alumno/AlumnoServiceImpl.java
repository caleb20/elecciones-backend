package com.caleb.elecciones.service.alumno;

import com.caleb.elecciones.model.Alumno;
import com.caleb.elecciones.repository.AlumnoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {
    private final AlumnoRepository alumnoRepository;

    @Override
    public Alumno guardar(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public List<Alumno> listar() {
        return alumnoRepository.findAll();
    }

    @Override
    public Alumno buscar(Long id) {
        return alumnoRepository.findById(id).orElse(new Alumno());
    }

    @Override
    public Alumno actualizar(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public void eliminar(Long id) {
        alumnoRepository.deleteById(id);
    }
}
