package com.caleb.elecciones.controller;

import com.caleb.elecciones.auth.AuthenticationService;
import com.caleb.elecciones.auth.JwtService;
import com.caleb.elecciones.model.Usuario;
import com.caleb.elecciones.request.LoginRequest;
import com.caleb.elecciones.request.SingupRequest;
import com.caleb.elecciones.response.GenericResponse;
import com.caleb.elecciones.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173/") // El origen de tu cliente
public class LoginController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public LoginController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Usuario> register(@RequestBody SingupRequest registerUserDto) {
        Usuario registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public GenericResponse<LoginResponse> authenticate(@RequestBody LoginRequest loginUserDto) {
        Usuario authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return new GenericResponse<>(true, loginResponse, "Ok");
    }
}
