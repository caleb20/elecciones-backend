package com.caleb.elecciones.controller;

import com.caleb.elecciones.model.Voto;
import com.caleb.elecciones.response.GenericResponse;
import com.caleb.elecciones.service.voto.VotoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votos")
@AllArgsConstructor
@CrossOrigin(origins = "*") // El origen de tu cliente
public class VotoController {
    private final VotoService votoService;

    @PostMapping("/registrar-voto")
    public GenericResponse<?> guardar(@RequestBody Voto voto) {
        return new GenericResponse<>(true, votoService.guardar(voto), "Ok");
    }
}
