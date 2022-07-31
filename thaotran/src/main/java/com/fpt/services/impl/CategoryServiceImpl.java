package com.fpt.services.impl;

import com.fpt.convert.CategoryConverter;
import com.fpt.dto.CategoryDto;
import com.fpt.entities.Category;
import com.fpt.repository.CategoryRepository;
import com.fpt.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;

    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category :
                categories) {
            categoryDtoList.add(categoryConverter.convertToDTO(category));
        }
        return categoryDtoList;
    }

    @Override
    public Page<Category> findByCategoryNameLike(Integer pageNumber, Integer pageSize, String keyword) {
        if (pageNumber == null) pageNumber = 0;
        if (pageSize == null) pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (keyword == null) keyword = "";
        return categoryRepository.findByCategoryNameLike(keyword, pageable);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {

        Category category = categoryConverter.convertToEntity(categoryDto);
        Category categorySave = categoryRepository.save(category);

        return categoryConverter.convertToDTO(categorySave);
    }

    @Override
    public Category update(Category category, String code) {

        return null;
    }

    @Override
    public boolean delete(String code) {
        try {
            categoryRepository.deleteById(code);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CategoryDto findById(String code) {
        Category category = categoryRepository.getById(code);
        return categoryConverter.convertToDTO(category);
    }


}
