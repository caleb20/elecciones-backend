package com.caleb.elecciones.controller;

import com.caleb.elecciones.auth.AuthenticationService;
import com.caleb.elecciones.auth.JwtService;
import com.caleb.elecciones.model.Usuario;
import com.caleb.elecciones.request.LoginRequest;
import com.caleb.elecciones.request.RefreshTokenRequest;
import com.caleb.elecciones.request.SingupRequest;
import com.caleb.elecciones.response.AuthResponse;
import com.caleb.elecciones.response.GenericResponse;
import com.caleb.elecciones.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private JwtService jwtService;
    private final AuthenticationService authenticationService;

    public LoginController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public GenericResponse<AuthResponse> signup(@RequestBody SingupRequest singupRequest) {
//
        return new GenericResponse<>(true, authenticationService.signup(singupRequest), "Ok");

    }

    @PostMapping("/login")
//    @CrossOrigin("*")
    public GenericResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return new GenericResponse<>(true, authenticationService.authenticate(loginRequest), "Ok");
    }

    @PostMapping("/refresh-token")
    public GenericResponse<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return new GenericResponse<>(true, authenticationService.refreshToken(request), "Ok");

    }

    @PostMapping("/logout")
    public GenericResponse<Boolean> logout(@RequestHeader("Authorization") String token) {
        authenticationService.logout(token);
        return new GenericResponse<>(true, true, "Ok");
    }
}
