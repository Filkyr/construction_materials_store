package com.netcracker.cmstore.service;

import com.netcracker.cmstore.model.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    void removeProduct(int productId);

    void updateProduct(Product product);

    List<Product> getProducts();

    Product getProductById(int productId);
}
