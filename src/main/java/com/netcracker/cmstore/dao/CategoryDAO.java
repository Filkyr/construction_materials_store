package com.netcracker.cmstore.dao;

import com.netcracker.cmstore.model.Category;

import java.util.List;

public interface CategoryDAO {

    void removeCategory(int categoryId);

    void updateOrInsertCategory(Category category);

    List<Category> getCategories();

    Category getCategoryById(int categoryId);
}
