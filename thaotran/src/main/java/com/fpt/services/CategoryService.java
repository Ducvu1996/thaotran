package com.fpt.services;

import com.fpt.dto.CategoryDto;
import com.fpt.entities.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    //public Map<String,Object> paging(int page, int size, String search, String field, String order);
    List<CategoryDto> getAll();

    Page<Category> findByCategoryNameLike(Integer pageNumber, Integer pageSize, String keyword);

    CategoryDto save(CategoryDto category);

    Category update(Category category, String code);

    boolean delete(String code);

    CategoryDto findById(String code);
}
