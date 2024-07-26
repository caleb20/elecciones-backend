package com.caleb.elecciones.service.voto;

import com.caleb.elecciones.model.Voto;

public interface VotoService {
    Voto verificarVoto(Long codigoAlumno);
    Voto guardar(Voto voto);
}
