package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.model.Category;
import com.netcracker.cmstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/")
@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    @GetMapping("/category/list")
    public String listCategory(ModelMap model) {
        List categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "ListCategory";
    }

    @PostMapping("/category/delete/{categoryId}")
    public String deleteCategory(@PathVariable String categoryId) {
        categoryService.removeCategory(Integer.valueOf(categoryId));
        return "redirect:/category/list";
    }

    @GetMapping("/category/insert")
    public String newCategory(ModelMap model) {
        Category category = new Category();
        model.addAttribute("category", category);
        model.addAttribute("edit", false);
        return "Category";
    }

    @PostMapping("/category/updateOrInsert")
    public String saveCategory(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "Category";
        }
        categoryService.insertOrUpdateCategory(category);
        return "redirect:/category/list";
    }

    @GetMapping("/category/edit/{categoryId}")
    public String editCategory(@PathVariable String categoryId, ModelMap model) {
        Category category = categoryService.getCategoryById(Integer.valueOf(categoryId));
        model.addAttribute("category", category);
        model.addAttribute("edit", true);
        return "Category";
    }

}
