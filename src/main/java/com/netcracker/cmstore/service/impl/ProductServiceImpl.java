package com.netcracker.cmstore.service.impl;

import com.netcracker.cmstore.dao.ProductDAO;
import com.netcracker.cmstore.model.Product;
import com.netcracker.cmstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDao;

    @Autowired
    public ProductServiceImpl(ProductDAO productDao) {
        this.productDao = productDao;
    }

    @Transactional
    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Transactional
    @Override
    public void removeProduct(int productId) {
        productDao.removeProduct(productId);
    }

    @Transactional
    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductById(int productId) {
        return productDao.getProductById(productId);
    }
}
