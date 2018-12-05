package com.netcracker.cmstore.service;

import com.netcracker.cmstore.model.Product;

import java.util.List;

public interface ProductService {
    void insertOrUpdateProduct(Product product);

    void removeProduct(int productId);

    List<Product> getProducts();

    Product getProductById(int productId);
}
