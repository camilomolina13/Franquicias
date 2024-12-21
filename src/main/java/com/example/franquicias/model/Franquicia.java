package com.example.franquicias.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity(name="FRANQUICIA")
public class Franquicia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "franquicia")
    @JsonIgnore
    private List<Sucursal> sucursales;

    public Franquicia() {
    }

    public Franquicia(Long id, String nombre, List<Sucursal> sucursales) {
        this.id = id;
        this.nombre = nombre;
        this.sucursales = sucursales;
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

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Franquicia that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(sucursales, that.sucursales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, sucursales);
    }
}
