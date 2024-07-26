package com.caleb.elecciones.service.voto;

import com.caleb.elecciones.model.Voto;
import com.caleb.elecciones.repository.VotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VotoServiceImpl implements VotoService{
    private final VotoRepository votoRepository;

    @Override
    public Voto verificarVoto(Long codigoAlumno) {
        return votoRepository.findByCodigoAlumno(codigoAlumno);
    }

    @Override
    public Voto guardar(Voto voto) {
        return votoRepository.save(voto);
    }
}
