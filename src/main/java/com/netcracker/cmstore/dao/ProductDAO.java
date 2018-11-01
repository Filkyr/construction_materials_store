package com.netcracker.cmstore.dao;

import com.netcracker.cmstore.model.Product;

import java.util.List;

public interface ProductDAO {
    void addProduct(Product product);

    void removeProduct(int productId);

    void updateProduct(Product product);

    List<Product> getProducts();

    Product getProductById(int productId);
}
