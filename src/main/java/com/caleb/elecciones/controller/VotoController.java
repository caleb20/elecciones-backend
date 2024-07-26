package com.caleb.elecciones.controller;

import com.caleb.elecciones.model.Voto;
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
    public Voto guardar(@RequestBody Voto voto) {
        return votoService.guardar(voto);
    }
}
