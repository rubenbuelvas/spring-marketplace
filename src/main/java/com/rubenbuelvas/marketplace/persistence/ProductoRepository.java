package com.rubenbuelvas.marketplace.persistence;

import com.rubenbuelvas.marketplace.domain.Product;
import com.rubenbuelvas.marketplace.domain.repository.ProductRepository;
import com.rubenbuelvas.marketplace.persistence.crud.ProductoCrudRepository;
import com.rubenbuelvas.marketplace.persistence.entity.Producto;
import com.rubenbuelvas.marketplace.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        return mapper.toProducts((List<Producto>) productoCrudRepository.findAll());
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarceProducts(int stock) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(stock, true);
        return productos.map(p -> mapper.toProducts(p));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(p -> mapper.toProduct(p));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}
