package com.example.franquicias.controller;

import com.example.franquicias.model.Sucursal;
import com.example.franquicias.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;

    @PostMapping
    public Mono<Sucursal> crearSucursal(@RequestBody Sucursal sucursal) {
        return sucursalService.crearSucursal(sucursal);
    }

    @PutMapping("/{id}")
    public Mono<Sucursal> actualizarNombreSucursal(@PathVariable Long id, @RequestBody String nuevoNombre) {
        return sucursalService.actualizarNombre(id, nuevoNombre);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminarSucursal(@PathVariable Long id) {
        return sucursalService.eliminarSucursal(id);
    }
}
