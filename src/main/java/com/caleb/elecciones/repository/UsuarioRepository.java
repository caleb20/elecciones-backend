package com.caleb.elecciones.repository;

import com.caleb.elecciones.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
//    @Query(value = "select * from usuario where correo = ?1 and password = ?2", nativeQuery = true)
//    Usuario login(String correo, String password);
    Optional<Usuario> findByCorreo(String correo);

}
