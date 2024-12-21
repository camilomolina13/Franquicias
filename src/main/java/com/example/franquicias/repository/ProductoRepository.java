package com.example.franquicias.repository;

import com.example.franquicias.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Modifying
    @Query(value = "UPDATE producto SET stock = :nuevoStock WHERE id = :idProducto", nativeQuery = true)
    int actualizarStockNative(@Param("idProducto") Long idProducto, @Param("nuevoStock") int nuevoStock);

    @Query(value =
            "SELECT p.id, p.nombre, p.stock, p.sucursal_id " +
                    "FROM producto p " +
                    "JOIN sucursal s ON p.sucursal_id = s.id " +
                    "WHERE s.franquicia_id = :franquiciaId " +
                    "AND p.stock = (SELECT MAX(p2.stock) FROM producto p2 WHERE p2.sucursal_id = s.id) " +
                    "ORDER BY s.id",
            nativeQuery = true)
    List<Producto> findProductoConMayorStockPorSucursal(@Param("franquiciaId") Long franquiciaId);

    void deleteById(Long id);
}

