package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.ProductoDTO;

public interface ProductoService {

    ProductoDTO registrarProducto(ProductoDTO productoDTO);

    List<ProductoDTO> listarProductos();

    ProductoDTO buscarProductoPorId(Long id);

    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO);

    void eliminarProducto(Long id);

}
