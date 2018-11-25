package com.netcracker.cmstore.dao.impl;

import com.netcracker.cmstore.dao.CategoryDAO;
import com.netcracker.cmstore.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private static final String INSERT_CATEGORY = "INSERT INTO category (title, description) VALUES (?, ?)";
    private static final String SELECT_CATEGORY = "SELECT * FROM category WHERE category.id =?";
    private static final String SELECT_CATEGORIES = "SELECT * FROM category";
    private static final String UPDATE_CATEGORY = "UPDATE category SET title=?, description=? WHERE category.id=?";
    private static final String DELETE_CATEGORY = "DELETE FROM category WHERE category.id=?";

    private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, rowNum) -> {
        Category category = new Category();
        category.setCategoryId(rs.getInt("id"));
        category.setTitle(rs.getString("title"));
        category.setDescription(rs.getString("description"));
        return category;
    };

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void addCategory(Category category) {
        jdbcTemplate.update(INSERT_CATEGORY, statement -> {
            statement.setString(1, category.getTitle());
            statement.setString(2, category.getDescription());
        });
    }

    @Override
    public void removeCategory(int categoryId) {
        jdbcTemplate.update(DELETE_CATEGORY, statement -> statement.setInt(1, categoryId));
    }

    @Override
    public void updateCategory(Category category) {
        jdbcTemplate.update(UPDATE_CATEGORY, statement -> {
            statement.setString(1, category.getTitle());
            statement.setString(2, category.getDescription());
            statement.setInt(3, category.getCategoryId());
        });
    }

    @Override
    public List<Category> getCategories() {
        return jdbcTemplate.query(SELECT_CATEGORIES, CATEGORY_ROW_MAPPER);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return jdbcTemplate.queryForObject(SELECT_CATEGORY, new Object[] { categoryId },CATEGORY_ROW_MAPPER);
    }
}

