package com.example.franquicias.controller;

import com.example.franquicias.model.Producto;
import com.example.franquicias.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    @PostMapping
    public Mono<Producto> agregarProducto(@RequestBody Producto producto) {
        return productoService.agregarProducto(producto);
    }

    @GetMapping
    public Flux<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @PutMapping("/{id}/stock")
    public Mono<ResponseEntity<String>> modificarStock(@PathVariable Long id, @RequestParam int nuevoStock) {
        return productoService.modificarStock(id, nuevoStock)
                .then(Mono.just(ResponseEntity.ok(
                        "El stock del producto con ID " + id + " se actualizó a " + nuevoStock)))  // Mensaje detallado
                .onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/{id}/productos-mayor-stock")
    public Mono<ResponseEntity<List<Producto>>> obtenerProductoConMayorStockPorSucursal(
            @PathVariable Long id) {
        return productoService.obtenerProductoConMayorStockPorSucursal(id)
                .map(productos -> ResponseEntity.ok(productos))
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> eliminarProducto(@PathVariable Long id) {
        return productoService.eliminarProducto(id)
                .then(Mono.just(ResponseEntity.noContent().build()))  // No content para indicar que se eliminó correctamente
                .onErrorResume(e -> {
                    // Si ocurre un error (producto no encontrado), se responde con 404
                    return Mono.just(ResponseEntity.notFound().build());
                });
    }
}
