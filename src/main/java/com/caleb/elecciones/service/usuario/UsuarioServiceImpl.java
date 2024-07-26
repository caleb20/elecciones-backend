package com.caleb.elecciones.service.usuario;

import com.caleb.elecciones.model.Usuario;
import com.caleb.elecciones.model.Voto;
import com.caleb.elecciones.repository.UsuarioRepository;
import com.caleb.elecciones.service.voto.VotoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final VotoService votoService;

    @Override
    public Usuario buscar(Long codigo) {
        return usuarioRepository.findById(codigo).get();
    }

}
