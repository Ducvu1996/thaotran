package com.fpt.controller;

import com.fpt.entities.Product;
import com.fpt.services.CategoryService;
import com.fpt.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String listProduct(Model model, @PageableDefault(size =4) Pageable pageable,
                              @RequestParam Optional<String> keyword) {
        String keywordOld = "";
        if (keyword.isPresent()) {
            keywordOld = keyword.get();

//            model.addAttribute("listProduct", productService.findAllByNameContaining(pageable, keywordOld));
        } else {
//            model.addAttribute("listProduct", productService.findAll(pageable));
            model.addAttribute("listProduct", productService.getAll());
        }
        model.addAttribute("keywordOld", keywordOld);

        model.addAttribute("product", new Product());
//        model.addAttribute("categoryList",this.categoryService.findAll());

        return "list_products";
    }
}
