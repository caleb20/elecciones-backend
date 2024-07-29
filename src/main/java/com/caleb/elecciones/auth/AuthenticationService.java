package com.caleb.elecciones.auth;

import com.caleb.elecciones.exception.VotoExistenteException;
import com.caleb.elecciones.model.Usuario;
import com.caleb.elecciones.model.Voto;
import com.caleb.elecciones.repository.UsuarioRepository;
import com.caleb.elecciones.request.LoginRequest;
import com.caleb.elecciones.request.RefreshTokenRequest;
import com.caleb.elecciones.request.SingupRequest;
import com.caleb.elecciones.response.AuthResponse;
import com.caleb.elecciones.response.LoginResponse;
import com.caleb.elecciones.service.voto.VotoService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final VotoService votoService;

    public AuthResponse signup(SingupRequest singupRequest) {
        Usuario user = new Usuario();
        user.setCodigo(singupRequest.getCodigo());
        user.setCorreo(singupRequest.getCorreo());
        user.setPassword(passwordEncoder.encode(singupRequest.getPassword()));

        Usuario usuario = usuarioRepository.save(user);
        usuario.setPassword(null);

        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new AuthResponse(jwtToken, refreshToken);
    }

    public LoginResponse authenticate(LoginRequest input) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getCorreo(), input.getPassword()));

        Usuario user = usuarioRepository.findByCorreo(input.getCorreo()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Voto voto = votoService.verificarVoto(user.getCodigo());

        if (voto != null) {
            throw new VotoExistenteException("El usuario ya ha votado");
        }

        // Invalidar tokens anteriores
        jwtService.invalidateAllTokens(user.getCorreo());

        String jwtToken = jwtService.generateToken(user);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return loginResponse;
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        String userEmail = jwtService.extractUsername(refreshToken);

        Usuario user = usuarioRepository.findByCorreo(userEmail).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (jwtService.isTokenValid(refreshToken, user)) {
            jwtService.invalidateToken(refreshToken);
            String newRefreshToken = jwtService.generateRefreshToken(user);

            return new AuthResponse(request.getRefreshToken(), newRefreshToken);
        }

        throw new RuntimeException("Invalid Refresh Token");
    }

    public void logout(String token) {
        String tokenWithoutBearer = token.substring(7);

        jwtService.invalidateToken(tokenWithoutBearer);
    }
}