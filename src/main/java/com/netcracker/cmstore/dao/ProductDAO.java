package com.netcracker.cmstore.dao;

import com.netcracker.cmstore.model.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
    private Connection connection;

    private static final String INSERT_PRODUCT = "INSERT INTO product (title, category_id, producer_id, image, description) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PRODUCT = "SELECT * FROM product WHERE product.id =?";
    private static final String SELECT_PRODUCTS = "SELECT * FROM product";
    private static final String UPDATE_PRODUCT = "UPDATE product SET title=?, category_id=?, producer_id=?, image=?,description=? WHERE product.id=?";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE product.id=?";

    public ProductDAO() {
        ConnectionClass con = new ConnectionClass();
        try {
            connection = con.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT);

            statement.setString(1, product.getTitle());
            statement.setInt(2, product.getCategoryId());
            statement.setInt(3, product.getProducerId());
            statement.setString(4, product.getImage());
            statement.setString(5, product.getDescription());

            int rows = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(int productId) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT);
            statement.setInt(1, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT);

            statement.setString(1, product.getTitle());
            statement.setInt(2, product.getCategoryId());
            statement.setInt(3, product.getProducerId());
            statement.setString(4, product.getImage());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getProductId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(SELECT_PRODUCTS);
        while (res.next()) {
            Product product = new Product();
            product.setProductId(res.getInt("id"));
            product.setTitle(res.getString("title"));
            product.setCategoryId(res.getInt("category_id"));
            product.setProducerId(res.getInt("producer_id"));
            product.setImage(res.getString("image"));
            product.setDescription(res.getString("description"));
            products.add(product);
        }
        return products;
    }

    public Product getProductById(int productId) throws SQLException {
        Product product = new Product();
        PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT);
        statement.setInt(1, productId);
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            product.setProductId(res.getInt("id"));
            product.setTitle(res.getString("title"));
            product.setCategoryId(res.getInt("category_id"));
            product.setProducerId(res.getInt("producer_id"));
            product.setImage(res.getString("image"));
            product.setDescription(res.getString("description"));
        }
        return product;
    }

}

