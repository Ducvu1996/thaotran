package com.fpt.convert;

import com.fpt.dto.CategoryDto;
import com.fpt.entities.Category;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CategoryConverter {


    public CategoryDto convertToDTO(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCode(category.getCode());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setCreateBy(category.getCreateBy());
        categoryDto.setUpdateBy(category.getUpdateBy());
        categoryDto.setUpdateAt(category.getUpdateAt());
        categoryDto.setCreateAt(category.getCreateAt());

        return categoryDto;
    }

    public Category convertToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCode(categoryDto.getCode());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCreateAt(Instant.now());
        category.setUpdateAt(Instant.now());
        return category;
    }
}
