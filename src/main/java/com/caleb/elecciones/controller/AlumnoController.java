package com.caleb.elecciones.controller;

import com.caleb.elecciones.model.Alumno;
import com.caleb.elecciones.service.alumno.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;

    @GetMapping
    public List<Alumno> listar(){
        return alumnoService.listar();
    }
}
