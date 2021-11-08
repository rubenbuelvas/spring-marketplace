package com.rubenbuelvas.marketplace.persistence.mapper;

import com.rubenbuelvas.marketplace.domain.Product;
import com.rubenbuelvas.marketplace.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mappings({
            @Mapping(target = "active", source = "estado"),
            @Mapping(target = "categoryId", source = "idCategoria"),
            @Mapping(target = "name", source = "nombre"),
            @Mapping(target = "price", source = "precioVenta"),
            @Mapping(target = "productId", source = "idProducto"),
            @Mapping(target = "stock", source = "cantidadStock")
    })
    Product toProduct(Producto producto);
    List<Product> toProducts(List<Producto> productos);

    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
}
