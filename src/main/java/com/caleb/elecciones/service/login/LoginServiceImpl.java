package com.caleb.elecciones.service.login;

import com.caleb.elecciones.model.Usuario;
import com.caleb.elecciones.model.Voto;
import com.caleb.elecciones.repository.UsuarioRepository;
import com.caleb.elecciones.response.LoginResponse;
import com.caleb.elecciones.service.usuario.UsuarioService;
import com.caleb.elecciones.service.voto.VotoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImpl  implements LoginService {
    private final UsuarioRepository usuarioRepository;
    private final VotoService votoService;

    @Override
    public LoginResponse login(String correo, String password) {
        LoginResponse loginResponse = null;

        // Primero se llama al servicio de login que es parte de Usuario
        Usuario usuario = usuarioRepository.login(correo, password);

        // Si el usuario existe entonces entra
        if (usuario != null) {

            // Obtiene el voto por código de usuario
            Voto voto = votoService.verificarVoto(usuario.getCodigo());

            // Si existe un registro de voto entonces devuelve null (no permite el login)
            if (voto != null) {
                return null;
            }
            loginResponse = new LoginResponse();
            loginResponse.setCodigo(usuario.getCodigo());
            loginResponse.setEmail(usuario.getCorreo());
        }


        return loginResponse;
    }


}
