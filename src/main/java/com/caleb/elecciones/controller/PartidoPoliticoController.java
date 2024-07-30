package com.caleb.elecciones.controller;

import com.caleb.elecciones.model.PartidoPolitico;
import com.caleb.elecciones.response.GenericResponse;
import com.caleb.elecciones.service.partidopolitico.PartidoPoliticoService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/partidos")
@RestController
@AllArgsConstructor
public class PartidoPoliticoController {
    private final PartidoPoliticoService partidoPoliticoService;

    @GetMapping("/listar")
    @PreAuthorize("hasRole('ROLE_ALUMNO')") // Solo accesible para usuarios con rol MAESTRO
    public GenericResponse<List<PartidoPolitico>> listar() {
        return partidoPoliticoService.listar();
    }

    @PostMapping("/guardar")
    @PreAuthorize("hasRole('ROLE_MAESTRO')") // Solo accesible para usuarios con rol MAESTRO
    public GenericResponse<PartidoPolitico> guardar(@RequestBody PartidoPolitico partidoPolitico) {
        return partidoPoliticoService.guardar(partidoPolitico);
    }

    @GetMapping("/hola")
    @PreAuthorize("hasRole('ROLE_MAESTRO')") // Solo accesible para usuarios con rol MAESTRO
    public String guardar() {
        return "hola mundo";
    }
}
