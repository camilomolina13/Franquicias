package com.example.franquicias.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity(name="SUCURSAL")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "franquicia_id")
    private Franquicia franquicia;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal")
    private List<Producto> productos;


    public Sucursal() {
    }

    public Sucursal(Long id, String nombre, Franquicia franquicia, List<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.franquicia = franquicia;
        this.productos = productos;
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

    public Franquicia getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(Franquicia franquicia) {
        this.franquicia = franquicia;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sucursal sucursal)) return false;
        return Objects.equals(id, sucursal.id) && Objects.equals(nombre, sucursal.nombre) && Objects.equals(franquicia, sucursal.franquicia) && Objects.equals(productos, sucursal.productos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, franquicia, productos);
    }
}
