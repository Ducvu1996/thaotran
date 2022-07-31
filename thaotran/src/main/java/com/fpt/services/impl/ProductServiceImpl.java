package com.fpt.services.impl;

import com.fpt.convert.ProductConverter;
import com.fpt.dto.ProductDto;
import com.fpt.entities.Product;
import com.fpt.repository.ProductRepository;
import com.fpt.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductConverter productConverter;

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product :
                products) {
            productDtoList.add(productConverter.convertToDTO(product));
        }
        return productDtoList;
    }

    @Override
    public Page<Product> findByNameProductLike(Integer pageNumber, Integer pageSize, String keyword, String category, String provider) {
        if (pageNumber == null) pageNumber = 0;
        if (pageSize == null) pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (keyword == null) keyword = "";
        if (category == null) category = "";
        if (provider == null) provider = "";

        return productRepository.findByNameProductLike(keyword, category, provider, pageable);
    }

    @Override
    public ProductDto save(ProductDto productDto) {

        Product product = productConverter.convertToEntity(productDto);
        Product productSave = productRepository.save(product);

        return productConverter.convertToDTO(productSave);

    }

    @Override
    public Product update(Product product, Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public ProductDto findById(Integer id) {
        Product product = productRepository.getById(id);
        return productConverter.convertToDTO(product);

    }
}
