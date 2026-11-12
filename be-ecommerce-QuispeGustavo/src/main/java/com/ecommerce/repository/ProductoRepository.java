package com.ecommerce.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query(name = "Producto.buscarPorNombre")
    List<Producto> buscarPorNombre(
        @Param("nombre") String nombre
    );

    @Query(name = "Producto.buscarPorCategoria")
    List<Producto> buscarPorCategoria(
        @Param("categoria") String categoria
    );

    @Query(name = "Producto.buscarPorCodigoBarras")
    Optional<Producto> buscarPorCodigoBarras(
        @Param("codigoBarras") String codigoBarras
    );

    @Query(name = "Producto.buscarPorRangoPrecios")
    List<Producto> buscarPorRangoPrecios(
        @Param("minimo") BigDecimal minimo,
        @Param("maximo") BigDecimal maximo
    );

}