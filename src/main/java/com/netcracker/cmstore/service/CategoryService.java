package com.netcracker.cmstore.service;

import com.netcracker.cmstore.model.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);

    void removeCategory(int categoryId);

    void updateCategory(Category category);

    List<Category> getCategories();

    Category getCategoryById(int categoryId);
}
