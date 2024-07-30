package com.caleb.elecciones.service.login;

import com.caleb.elecciones.model.Usuario;
import com.caleb.elecciones.model.Voto;
import com.caleb.elecciones.repository.UsuarioRepository;
import com.caleb.elecciones.response.LoginResponse;
import com.caleb.elecciones.service.voto.VotoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class LoginServiceImpl  implements LoginService {
    private final UsuarioRepository usuarioRepository;
    private final VotoService votoService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getPassword())
                .authorities(new ArrayList<>()) // Sin roles
                .build();
    }

//    @Override
//    public LoginResponse login(String correo, String password) {
//        LoginResponse loginResponse = null;

        // Primero se llama al servicio de login que es parte de Usuario
//        Usuario usuario = usuarioRepository.login(correo, password);

        // Si el usuario existe entonces entra
//        if (usuario != null) {
//
//            // Obtiene el voto por código de usuario
//            Voto voto = votoService.verificarVoto(usuario.getCodigo());
//
//            // Si existe un registro de voto entonces devuelve null (no permite el login)
//            if (voto != null) {
//                return null;
//            }
//            loginResponse = new LoginResponse();
//            loginResponse.setCodigo(usuario.getCodigo());
//            loginResponse.setEmail(usuario.getCorreo());
//        }


//        return loginResponse;
//    }


}
