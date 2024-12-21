package com.example.franquicias.service;

import com.example.franquicias.model.Franquicia;
import com.example.franquicias.model.Producto;
import com.example.franquicias.repository.FranquiciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FranquiciaService {
    @Autowired
    private FranquiciaRepository franquiciaRepository;

    public Mono<Franquicia> crearFranquicia(Franquicia franquicia) {
        return Mono.just(franquiciaRepository.save(franquicia));
    }


    public Mono<Franquicia> actualizarNombre(Long id, String nuevoNombre) {
        return Mono.justOrEmpty(franquiciaRepository.findById(id))
                .map(franquicia -> {
                    franquicia.setNombre(nuevoNombre);
                    return franquiciaRepository.save(franquicia);
                });
    }



    public Mono<Void> eliminarFranquicia(Long id) {
        franquiciaRepository.deleteById(id);
        return Mono.empty();
    }
}
