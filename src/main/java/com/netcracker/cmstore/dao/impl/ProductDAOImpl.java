package com.netcracker.cmstore.dao.impl;

import com.netcracker.cmstore.dao.ProductDAO;
import com.netcracker.cmstore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private static final String INSERT_PRODUCT = "INSERT INTO product (title, category_id, producer_id, image, description) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PRODUCT = "SELECT * FROM product WHERE product.id =?";
    private static final String SELECT_PRODUCTS = "SELECT * FROM product";
    private static final String UPDATE_PRODUCT = "UPDATE product SET title=?, category_id=?, producer_id=?, image=?,description=? WHERE product.id=?";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE product.id=?";

    private static final RowMapper<Product> PRODUCT_ROW_MAPPER = (rs, rowNum) -> {
        Product product = new Product();

        product.setProductId(rs.getInt("id"));
        product.setTitle(rs.getString("title"));
        product.setCategoryId(rs.getInt("category_id"));
        product.setProducerId(rs.getInt("producer_id"));
        product.setImage(rs.getString("image"));
        product.setDescription(rs.getString("description"));

        return product;
    };

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addProduct(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT, statement -> {
            statement.setString(1, product.getTitle());
            statement.setInt(2, product.getCategoryId());
            statement.setInt(3, product.getProducerId());
            statement.setString(4, product.getImage());
            statement.setString(5, product.getDescription());
        });
    }

    @Override
    public void removeProduct(int productId) {
        jdbcTemplate.update(DELETE_PRODUCT, statement -> statement.setInt(1, productId));
    }

    @Override
    public void updateProduct(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCT, statement -> {
            statement.setString(1, product.getTitle());
            statement.setInt(2, product.getCategoryId());
            statement.setInt(3, product.getProducerId());
            statement.setString(4, product.getImage());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getProductId());
        });
    }

    @Override
    public List<Product> getProducts() {
        return jdbcTemplate.query(SELECT_PRODUCTS, PRODUCT_ROW_MAPPER);
    }

    @Override
    public Product getProductById(int productId) {
        return jdbcTemplate.queryForObject(SELECT_PRODUCT, new Object[]{productId}, PRODUCT_ROW_MAPPER);
    }

}

