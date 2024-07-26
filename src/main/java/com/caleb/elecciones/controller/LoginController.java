package com.caleb.elecciones.controller;

import com.caleb.elecciones.request.LoginRequest;
import com.caleb.elecciones.response.LoginResponse;
import com.caleb.elecciones.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/") // El origen de tu cliente
public class LoginController {
    private final LoginService loginService;

    // Login que recibe por parámetro(@RequestBody) el objeto usuario para enviar el correo y password al servicio
    // que se encarga de realizar la lógica para saber si existe el usuario y si es que ya votó o todavía
    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
