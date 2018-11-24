package com.netcracker.cmstore.dao.impl;

import com.netcracker.cmstore.dao.CategoryDAO;
import com.netcracker.cmstore.dao.exception.DaoException;
import com.netcracker.cmstore.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private static final String INSERT_CATEGORY = "INSERT INTO category (title, description) VALUES (?, ?)";
    private static final String SELECT_CATEGORY = "SELECT * FROM category WHERE category.id =?";
    private static final String SELECT_CATEGORIES = "SELECT * FROM category";
    private static final String UPDATE_CATEGORY = "UPDATE category SET title=?, description=? WHERE category.id=?";
    private static final String DELETE_CATEGORY = "DELETE FROM category WHERE category.id=?";

    private final DataSource dataSource;

    @Autowired
    public CategoryDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addCategory(Category category) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY)) {
                statement.setString(1, category.getTitle());
                statement.setString(2, category.getDescription());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeCategory(int categoryId) {
        try(Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY)) {
                statement.setInt(1, categoryId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateCategory(Category category) {
        try (Connection connection = dataSource.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY)) {
                statement.setString(1, category.getTitle());
                statement.setString(2, category.getDescription());
                statement.setInt(3, category.getCategoryId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Category> getCategories() {
        try (Connection connection = dataSource.getConnection()){
            List<Category> categories = new ArrayList<>();
            try(Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(SELECT_CATEGORIES)) {
                while (res.next()) {
                    Category category = new Category();
                    category.setCategoryId(res.getInt("id"));
                    category.setTitle(res.getString("title"));
                    category.setDescription(res.getString("description"));
                    categories.add(category);
                }
            }
            return categories;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Category getCategoryById(int categoryId) {
        try (Connection connection = dataSource.getConnection()){
            Category category = new Category();
            try(PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORY)) {
                statement.setInt(1, categoryId);
                try (ResultSet res = statement.executeQuery()) {
                    if (res.next()) {
                        category.setCategoryId(res.getInt("id"));
                        category.setTitle(res.getString("title"));
                        category.setDescription(res.getString("description"));
                    }
                }
            }
            return category;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

