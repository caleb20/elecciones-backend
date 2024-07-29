package com.caleb.elecciones.controller;

import com.caleb.elecciones.model.PartidoPolitico;
import com.caleb.elecciones.response.GenericResponse;
import com.caleb.elecciones.service.partidopolitico.PartidoPoliticoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/partidos")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*") // El origen de tu cliente
public class PartidoPoliticoController {
    private final PartidoPoliticoService partidoPoliticoService;

    @GetMapping("/listar")
    public GenericResponse<List<PartidoPolitico>> listar() {
        return partidoPoliticoService.listar();
    }
}
