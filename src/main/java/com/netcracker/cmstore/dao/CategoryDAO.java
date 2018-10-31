package com.netcracker.cmstore.dao;

import java.sql.*;
import java.util.ArrayList;

import com.netcracker.cmstore.model.Category;

public class CategoryDAO {
    private Connection connection;

    private static final String INSERT_CATEGORY = "INSERT INTO category (title, description) VALUES (?, ?)";
    private static final String SELECT_CATEGORY = "SELECT * FROM category WHERE category.id =?";
    private static final String SELECT_CATEGORIES = "SELECT * FROM category";
    private static final String UPDATE_CATEGORY = "UPDATE category SET title=?, description=? WHERE category.id=?";
    private static final String DELETE_CATEGORY = "DELETE FROM category WHERE category.id=?";

    public CategoryDAO() {
        ConnectionClass con = new ConnectionClass();
        try {
            connection = con.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCategory(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY);
            statement.setString(1, category.getTitle());
            statement.setString(2, category.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCategory(int categoryId) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY);
            statement.setInt(1, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCategory(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY);
            statement.setString(1, category.getTitle());
            statement.setString(2, category.getDescription());
            statement.setInt(3, category.getCategoryId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Category> getCategories() throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(SELECT_CATEGORIES);
        while (res.next()) {
            Category category = new Category();
            category.setCategoryId(res.getInt("id"));
            category.setTitle(res.getString("title"));
            category.setDescription(res.getString("description"));
            categories.add(category);
        }
        categories.forEach((cat) -> System.out.println(cat.getCategoryId() + " - " + cat.getTitle() + " - " + cat.getDescription()));
        return categories;
    }

    public Category getCategoryById(int categoryId) throws SQLException {
        Category category = new Category();
        PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORY);
        statement.setInt(1, categoryId);
//        String query = "select * from category where category.id = " + categoryId + " ";
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            category.setCategoryId(res.getInt("id"));
            category.setTitle(res.getString("title"));
            category.setDescription(res.getString("description"));
        }
        return category;
    }

}

