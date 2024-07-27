package com.caleb.elecciones.auth;

import com.caleb.elecciones.model.Usuario;
import com.caleb.elecciones.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UsuarioRepository usuarioRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario signup(Usuario input) {
        Usuario user = new Usuario();
        user.setCodigo(input.getCodigo());
        user.setCorreo(input.getCorreo());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return usuarioRepository.save(user);
    }

    public Usuario authenticate(Usuario input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getCorreo(),
                        input.getPassword()
                )
        );

        return usuarioRepository.findByCorreo(input.getCorreo())
                .orElseThrow();
    }
}