package com.example.franquicias.service;

import com.example.franquicias.model.Sucursal;
import com.example.franquicias.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;

    public Mono<Sucursal> crearSucursal(Sucursal sucursal) {
        return Mono.just(sucursalRepository.save(sucursal));
    }

    public Mono<Sucursal> actualizarNombre(Long id, String nuevoNombre) {
        return Mono.justOrEmpty(sucursalRepository.findById(id))
                .map(sucursal -> {
                    sucursal.setNombre(nuevoNombre);
                    return sucursalRepository.save(sucursal);
                });
    }

    public Mono<Void> eliminarSucursal(Long id) {
        sucursalRepository.deleteById(id);
        return Mono.empty();
    }
}
