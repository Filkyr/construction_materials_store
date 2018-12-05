package com.netcracker.cmstore.dao;

import com.netcracker.cmstore.model.Product;

import java.util.List;

public interface ProductDAO {
    void insertOrUpdateProduct(Product product);

    void removeProduct(int productId);

    List<Product> getProducts();

    Product getProductById(int productId);
}
