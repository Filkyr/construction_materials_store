package com.netcracker.cmstore.dao;

import com.netcracker.cmstore.model.Category;

import java.util.List;

public interface CategoryDAO {
    void addCategory(Category category);

    void removeCategory(int categoryId);

    void updateCategory(Category category);

    List<Category> getCategories();

    Category getCategoryById(int categoryId);
}
