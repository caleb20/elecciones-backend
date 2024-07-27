package com.caleb.elecciones.response;

import lombok.Data;

@Data
public class LoginResponse {
    private Long codigo;
    private String email;
    private String token;
    private long expiresIn;
}
