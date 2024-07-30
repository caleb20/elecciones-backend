package com.caleb.elecciones.service.partidopolitico;

import com.caleb.elecciones.model.PartidoPolitico;
import com.caleb.elecciones.repository.PartidoPoliticoRepository;
import com.caleb.elecciones.response.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PartidoPoliticoServiceImpl implements PartidoPoliticoService {
    private final PartidoPoliticoRepository partidoPoliticoRepository;

    @Override
    public GenericResponse<PartidoPolitico> guardar(PartidoPolitico partidoPolitico) {
        return new GenericResponse<>(true, partidoPoliticoRepository.save(partidoPolitico), "Ok");
    }

    @Override
    public GenericResponse<List<PartidoPolitico>> listar() {
        List<PartidoPolitico> partidos = partidoPoliticoRepository.findAll();
        return new GenericResponse<>(true, partidos, "Ok");
    }

    @Override
    public PartidoPolitico buscar(Integer codigo) {
        return null;
    }

    @Override
    public PartidoPolitico actualizar(PartidoPolitico alumno) {
        return null;
    }

    @Override
    public void eliminar(Integer codigo) {

    }
}
