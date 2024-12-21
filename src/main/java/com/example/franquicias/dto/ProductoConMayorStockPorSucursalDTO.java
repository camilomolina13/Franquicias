package com.example.franquicias.dto;

public class ProductoConMayorStockPorSucursalDTO {

    private Long productoId;
    private String nombreProducto;
    private int stock;
    private Long sucursalId;

    // Constructor
    public ProductoConMayorStockPorSucursalDTO(Long productoId, String nombreProducto, int stock, Long sucursalId) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.stock = stock;
        this.sucursalId = sucursalId;
    }

    // Getters y Setters
    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }
}

