package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.model.Product;
import com.netcracker.cmstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/")
@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    @GetMapping("/product/list")
    public String listProduct(ModelMap model) {
        List products = productService.getProducts();
        model.addAttribute("products", products);
        return "ListProduct";
    }

    @PostMapping("/product/delete/{productId}")
    public String deleteProduct(@PathVariable String productId) {
        productService.removeProduct(Integer.valueOf(productId));
        return "redirect:/product/list";
    }

    @GetMapping("/product/insert")
    public String newProduct(ModelMap model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("edit", false);
        return "Product";
    }

    @PostMapping("/product/updateOrInsert")
    public String saveProduct(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "Product";
        }
        productService.insertOrUpdateProduct(product);
        return "redirect:/product/list";
    }

    @GetMapping("/product/edit/{productId}")
    public String editProduct(@PathVariable String productId, ModelMap model) {
        Product product = productService.getProductById(Integer.valueOf(productId));
        model.addAttribute("product", product);
        model.addAttribute("edit", true);
        return "Product";
    }

}

