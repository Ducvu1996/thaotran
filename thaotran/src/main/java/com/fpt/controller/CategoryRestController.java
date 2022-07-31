package com.fpt.controller;

import com.fpt.convert.CategoryConverter;
import com.fpt.dto.CategoryDto;
import com.fpt.entities.Category;
import com.fpt.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/category")
public class CategoryRestController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryConverter categoryConverter;

    @ResponseBody
    @GetMapping("/list")
    public ResponseEntity<List<CategoryDto>> getAll() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/paging/")
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryDto> list(@RequestParam(value = "page", required = false) Integer pageNum,
                                  @RequestParam(value = "size", required = false) Integer pageSize,
                                  @RequestParam(value = "title", required = false) String title
    ) {

        Page<Category> pageEntity = categoryService.findByCategoryNameLike(pageNum, pageSize, title);
        Page<CategoryDto> pageDTO = pageEntity.map(category -> categoryConverter.convertToDTO(category));
        return pageDTO;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody @Validated CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.save(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<CategoryDto> editCategory(@RequestBody @Validated CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.save(categoryDto), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{code}")
    public ResponseEntity<String> deleteCategory(@PathVariable("code") String code) {
        return new ResponseEntity<>(categoryService.delete(code) ? "Delete Success" : "Delete Failed", HttpStatus.OK);
    }

    @GetMapping(value = "/detail/{code}")
    public ResponseEntity<CategoryDto> detailCategory(@PathVariable("code") String code) {
        return new ResponseEntity<>(categoryService.findById(code), HttpStatus.OK);
    }
}
