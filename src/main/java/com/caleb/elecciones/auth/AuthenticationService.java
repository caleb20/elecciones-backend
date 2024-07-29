package com.caleb.elecciones.auth;

import com.caleb.elecciones.exception.VotoExistenteException;
import com.caleb.elecciones.model.Usuario;
import com.caleb.elecciones.model.Voto;
import com.caleb.elecciones.repository.UsuarioRepository;
import com.caleb.elecciones.request.LoginRequest;
import com.caleb.elecciones.request.RefreshTokenRequest;
import com.caleb.elecciones.request.SingupRequest;
import com.caleb.elecciones.response.AuthResponse;
import com.caleb.elecciones.service.voto.VotoService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final VotoService votoService;

    public Usuario signup(SingupRequest input) {
        Usuario user = new Usuario();
        user.setCodigo(input.getCodigo());
        user.setCorreo(input.getCorreo());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        Usuario usuario = usuarioRepository.save(user);
        usuario.setPassword(null);

        return usuario;
    }

    public Usuario authenticate(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getCorreo(),
                        input.getPassword()
                )
        );

        Optional<Usuario> user = usuarioRepository.findByCorreo(input.getCorreo());
        Voto voto = votoService.verificarVoto(user.orElseThrow().getCodigo());

        if (voto != null) {
            throw new VotoExistenteException("El usuario ya ha votado");
        }

        return user.orElseThrow();
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        String userEmail = jwtService.extractUsername(refreshToken);

        Usuario user = usuarioRepository.findByCorreo(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (jwtService.isTokenValid(refreshToken, user)) {
            String jwtToken = jwtService.generateToken(user);
            return new AuthResponse(jwtToken, refreshToken);
        }

        throw new RuntimeException("Invalid Refresh Token");
    }
}