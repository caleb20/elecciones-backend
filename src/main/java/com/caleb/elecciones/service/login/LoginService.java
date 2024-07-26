package com.caleb.elecciones.service.login;

import com.caleb.elecciones.response.LoginResponse;

public interface LoginService {

    LoginResponse login(String correo, String password);

}
