package com.caleb.elecciones.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String correo;
    private String password;
}
