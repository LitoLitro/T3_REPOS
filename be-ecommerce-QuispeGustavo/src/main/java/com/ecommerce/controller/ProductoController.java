package com.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.ecommerce.dto.ProductoDTO;
import com.ecommerce.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> registrarProducto(
            @Valid @RequestBody ProductoDTO productoDTO) {

        ProductoDTO nuevoProducto =
                productoService.registrarProducto(productoDTO);

        return new ResponseEntity<>(
                nuevoProducto,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() {

        List<ProductoDTO> productos =
                productoService.listarProductos();

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscarProducto(
            @PathVariable Long id) {

        ProductoDTO producto =
                productoService.buscarProductoPorId(id);

        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO productoDTO) {

        ProductoDTO productoActualizado =
                productoService.actualizarProducto(id, productoDTO);

        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
            @PathVariable Long id) {

        productoService.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }
}