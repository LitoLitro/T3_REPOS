package com.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")

@NamedQueries({

    @NamedQuery(
        name = "Producto.buscarPorNombre",
        query = "SELECT p FROM Producto p " +
                "WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))"
    ),

    @NamedQuery(
        name = "Producto.buscarPorCategoria",
        query = "SELECT p FROM Producto p " +
                "WHERE LOWER(p.categoria) = LOWER(:categoria)"
    ),

    @NamedQuery(
        name = "Producto.buscarPorCodigoBarras",
        query = "SELECT p FROM Producto p " +
                "WHERE p.codigoBarras = :codigoBarras"
    ),

    @NamedQuery(
        name = "Producto.buscarPorRangoPrecios",
        query = "SELECT p FROM Producto p " +
                "WHERE p.precioVenta BETWEEN :minimo AND :maximo"
    )

})

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String categoria;

    @Column(name = "precio_compra", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioCompra;

    @Column(name = "precio_venta", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioVenta;

    @Column(nullable = false)
    private Integer stock;

    @Column(length = 100)
    private String marca;

    @Column(name = "codigo_barras", length = 50, unique = true)
    private String codigoBarras;

    @Column(name = "fecha_ingreso", nullable = false, updatable = false)
    private LocalDateTime fechaIngreso;

    public Producto() {
    }

    @PrePersist
    public void asignarFechaIngreso() {
        if (this.fechaIngreso == null) {
            this.fechaIngreso = LocalDateTime.now();
        }
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}