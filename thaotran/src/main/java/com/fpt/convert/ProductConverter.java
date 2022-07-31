package com.fpt.convert;


import com.fpt.dto.ProductDto;
import com.fpt.entities.Product;
import com.fpt.repository.CategoryRepository;
import com.fpt.repository.ProviderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ProductConverter {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    CategoryConverter categoryConverter;
    @Autowired
    ProviderConverter providerConverter;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProviderRepository providerRepository;

    public ProductDto convertToDTO(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setIdProduct(product.getIdProduct());
        productDto.setNameProduct(product.getNameProduct());
        productDto.setCreateAt(product.getCreateAt());
        productDto.setCreateBy(product.getCreateBy());
        productDto.setImagePath(product.getImagePath());
        productDto.setQuantity(product.getQuantity());
        productDto.setUpdateAt(product.getUpdateAt());
        productDto.setUpdateBy(product.getUpdateBy());


        if (null != product.getProducer()) {
            productDto.setProducer(product.getProducer().getIdProvider());
        }
        if (null != product.getProducer()) {
            productDto.setSupplier(product.getSupplier().getIdProvider());
        }
        if (null != product.getCategory()) {
            productDto.setCategory(product.getCategory().getCode());
        }
        return productDto;
    }

    public Product convertToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setIdProduct(productDto.getIdProduct());
        product.setNameProduct(productDto.getNameProduct());
        product.setCreateAt(Instant.now());
        product.setCreateBy(productDto.getCreateBy());
        product.setImagePath(productDto.getImagePath());
        product.setQuantity(productDto.getQuantity());
        product.setUpdateAt(Instant.now());
        product.setUpdateBy(productDto.getUpdateBy());
        product.setCategory(categoryRepository.findById(productDto.getCategory()).get());
        product.setProducer(providerRepository.findById(productDto.getProducer()).get());
        product.setSupplier(providerRepository.findById(productDto.getSupplier()).get());
        return product;
    }
}
