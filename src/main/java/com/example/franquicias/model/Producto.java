package com.example.franquicias.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name="PRODUCTO")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int stock;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    public Producto() {
    }

    public Producto(Long id, String nombre, int stock, Sucursal sucursal) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.sucursal = sucursal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto producto)) return false;
        return stock == producto.stock && Objects.equals(id, producto.id) && Objects.equals(nombre, producto.nombre) && Objects.equals(sucursal, producto.sucursal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, stock, sucursal);
    }
}
