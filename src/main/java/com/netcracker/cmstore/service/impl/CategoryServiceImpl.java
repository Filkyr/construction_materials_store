package com.netcracker.cmstore.service.impl;

import com.netcracker.cmstore.dao.CategoryDAO;
import com.netcracker.cmstore.model.Category;
import com.netcracker.cmstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional
    @Override
    public void removeCategory(int categoryId) {
        categoryDao.removeCategory(categoryId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }

    @Transactional(readOnly = true)
    @Override
    public Category getCategoryById(int categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

    @Transactional
    @Override
    public void insertOrUpdateCategory(Category category) {
        categoryDao.updateOrInsertCategory(category);
    }
}
