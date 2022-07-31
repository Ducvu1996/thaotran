package com.fpt.controller;

import com.fpt.convert.ProductConverter;
import com.fpt.dto.ProductDto;
import com.fpt.entities.Product;
import com.fpt.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductRestController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductConverter productConverter;

    @ResponseBody
    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/paging")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductDto> list(@RequestParam(value = "page", required = false) Integer pageNum,
                                 @RequestParam(value = "size", required = false) Integer pageSize,
                                 @RequestParam(value = "title", required = false) String title,
                                 @RequestParam(value = "category", required = false) String category,
                                 @RequestParam(value = "provider", required = false) String provider
    ) {

        Page<Product> pageEntity = productService.findByNameProductLike(pageNum, pageSize, title, category, provider);
        Page<ProductDto> pageDTO = pageEntity.map(product -> productConverter.convertToDTO(product));
        return pageDTO;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody @Validated ProductDto productDto) {
//        SecurityContextHolder.getContext().
        return new ResponseEntity<>(productService.save(productDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<ProductDto> editProduct(@RequestBody @Validated ProductDto productDto) {

        return new ResponseEntity<>(productService.save(productDto), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(productService.delete(id) ? "Delete Success" : "Delete Failed", HttpStatus.OK);
    }

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<ProductDto> detailProduct(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }
}
