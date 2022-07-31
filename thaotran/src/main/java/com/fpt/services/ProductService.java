package com.fpt.services;


import com.fpt.dto.ProductDto;
import com.fpt.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    Page<Product> findByNameProductLike(Integer pageNumber, Integer pageSize, String keyword, String category, String provider);

    ProductDto save(ProductDto product);

    Product update(Product product, Integer id);

    boolean delete(Integer id);

    ProductDto findById(Integer id);
}
