package com.rubenbuelvas.marketplace.persistence;

import com.rubenbuelvas.marketplace.persistence.crud.ProductoCrudRepository;
import com.rubenbuelvas.marketplace.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll() {
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria) {
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidadStock) {
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidadStock, true);
    }

    public Optional<Producto> getProducto(int idProducto) {
        return productoCrudRepository.findById(idProducto);
    }

    public Producto saveProducto(Producto producto) {
        return productoCrudRepository.save(producto);
    }

    public void deleteProductoById(int idProducto) {
        productoCrudRepository.deleteById(idProducto);
    }
}
