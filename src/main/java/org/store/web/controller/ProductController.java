package org.store.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.store.service.ProductService;
import org.store.web.entity.Product;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getAll(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Product product) {
        productService.saveProduct(product);
        return "products";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public String edit(@RequestBody Product product) {
        productService.saveProduct(product);
        return "products";
    }

    @DeleteMapping(value = "/delete?={id}")
    public String delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "products";
    }
}
