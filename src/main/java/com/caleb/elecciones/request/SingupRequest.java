package com.caleb.elecciones.request;

import lombok.Data;

@Data
public class SingupRequest {
    private Long codigo;
    private String correo;
    private String password;
}
