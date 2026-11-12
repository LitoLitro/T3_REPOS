package com.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.dto.ProductoDTO;
import com.ecommerce.entity.Producto;
import com.ecommerce.repository.ProductoRepository;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    private ProductoDTO convertirDTO(Producto producto) {

        ProductoDTO dto = new ProductoDTO();

        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setCategoria(producto.getCategoria());
        dto.setPrecioCompra(producto.getPrecioCompra());
        dto.setPrecioVenta(producto.getPrecioVenta());
        dto.setStock(producto.getStock());
        dto.setMarca(producto.getMarca());
        dto.setCodigoBarras(producto.getCodigoBarras());
        dto.setFechaIngreso(producto.getFechaIngreso());

        return dto;
    }

    private Producto convertirEntidad(ProductoDTO dto) {

        Producto producto = new Producto();

        producto.setNombre(dto.getNombre());
        producto.setCategoria(dto.getCategoria());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setStock(dto.getStock());
        producto.setMarca(dto.getMarca());
        producto.setCodigoBarras(dto.getCodigoBarras());

        return producto;
    }

    @Override
    public ProductoDTO registrarProducto(ProductoDTO productoDTO) {

        Producto producto = convertirEntidad(productoDTO);

        Producto productoGuardado = productoRepository.save(producto);

        return convertirDTO(productoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> listarProductos() {

        return productoRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDTO buscarProductoPorId(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Producto no encontrado"
                ));

        return convertirDTO(producto);
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Producto no encontrado"
                ));

        producto.setNombre(productoDTO.getNombre());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setPrecioCompra(productoDTO.getPrecioCompra());
        producto.setPrecioVenta(productoDTO.getPrecioVenta());
        producto.setStock(productoDTO.getStock());
        producto.setMarca(productoDTO.getMarca());
        producto.setCodigoBarras(productoDTO.getCodigoBarras());

        Producto actualizado = productoRepository.save(producto);

        return convertirDTO(actualizado);
    }

    @Override
    public void eliminarProducto(Long id) {

        if (!productoRepository.existsById(id)) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Producto no encontrado"
            );
        }

        productoRepository.deleteById(id);
    }
}