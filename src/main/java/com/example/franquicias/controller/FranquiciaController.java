package com.example.franquicias.controller;

import com.example.franquicias.model.Franquicia;
import com.example.franquicias.model.Producto;
import com.example.franquicias.service.FranquiciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franquiciaService;

    @PostMapping
    public Mono<Franquicia> crearFranquicia(@RequestBody Franquicia franquicia) {
        return franquiciaService.crearFranquicia(franquicia);
    }

    @PutMapping("/{id}")
    public Mono<Franquicia> actualizarNombreFranquicia(@PathVariable Long id, @RequestBody String nuevoNombre) {
        return franquiciaService.actualizarNombre(id, nuevoNombre);
    }



    @DeleteMapping("/{id}")
    public Mono<Void> eliminarFranquicia(@PathVariable Long id) {
        return franquiciaService.eliminarFranquicia(id);
    }
}
