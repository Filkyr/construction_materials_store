package com.netcracker.cmstore.service;

import com.netcracker.cmstore.model.Category;

import java.util.List;

public interface CategoryService {
    void removeCategory(int categoryId);

    List<Category> getCategories();

    Category getCategoryById(int categoryId);

    void insertOrUpdateCategory(Category category);
}
