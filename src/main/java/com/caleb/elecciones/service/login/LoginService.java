package com.caleb.elecciones.service.login;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LoginService {
    UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException;
//    LoginResponse login(String correo, String password);

}
