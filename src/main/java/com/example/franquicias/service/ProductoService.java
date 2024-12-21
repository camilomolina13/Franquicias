package com.example.franquicias.service;

import com.example.franquicias.dto.ProductoConMayorStockPorSucursalDTO;
import com.example.franquicias.exception.NotFoundException;
import com.example.franquicias.model.Producto;
import com.example.franquicias.repository.ProductoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @PersistenceContext
    private EntityManager entityManager;


    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Mono<Producto> agregarProducto(Producto producto) {
        return Mono.just(productoRepository.save(producto));
    }

    public Flux<Producto> listarProductos() {
        return Flux.fromIterable(productoRepository.findAll());
    }

    @Transactional
    public Mono<Producto> actualizarStock(Long id, int nuevoStock) {
        return Mono.fromCallable(() -> {
            Producto producto = entityManager.find(Producto.class, id);
            if (producto == null) {
                throw new NotFoundException("Producto no encontrado");
            }
            producto.setStock(nuevoStock);
            entityManager.merge(producto);
            return producto;
        });
    }

    public Mono<List<Producto>> obtenerProductoConMayorStockPorSucursal(Long franquiciaId) {
        return Mono.fromCallable(() -> productoRepository.findProductoConMayorStockPorSucursal(franquiciaId));
    }

    public Mono<Void> eliminarProducto(Long productoId) {
        return Mono.fromRunnable(() -> {
            boolean productoExiste = productoRepository.existsById(productoId);
            if (!productoExiste) {
                // Si el producto no existe, devolvemos Mono.error para propagar un error
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
            }
            productoRepository.deleteById(productoId); // Si existe, lo eliminamos
        });
    }

    @Transactional
    public Mono<Void> modificarStock(Long idProducto, int nuevoStock) {
        int updatedRows = productoRepository.actualizarStockNative(idProducto, nuevoStock);
        if (updatedRows == 0) {
            return Mono.error(new RuntimeException("Producto no encontrado"));
        }
        return Mono.empty();
    }


}
