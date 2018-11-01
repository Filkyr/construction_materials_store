package com.netcracker.cmstore.dao.factory;

import com.netcracker.cmstore.dao.*;
import com.netcracker.cmstore.dao.impl.*;

public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    private CategoryDAO categoryDAO = new CategoryDAOImpl(DataSourceFactory.getDataSourceFactory().getDataSource());
    private CustomerDAO customerDAO = new CustomerDAOImpl(DataSourceFactory.getDataSourceFactory().getDataSource());
    private OrderDAO orderDAO = new OrderDAOImpl(DataSourceFactory.getDataSourceFactory().getDataSource());
    private OrderProductDAO orderProductDAO = new OrderProductDAOImpl(DataSourceFactory.getDataSourceFactory().getDataSource());
    private ProducerDAO producerDAO = new ProducerDAOImpl(DataSourceFactory.getDataSourceFactory().getDataSource());
    private ProductDAO ProductAO = new ProductDAOImpl(DataSourceFactory.getDataSourceFactory().getDataSource());

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public OrderProductDAO getOrderProductDAO() {
        return orderProductDAO;
    }

    public ProducerDAO getProducerDAO() {
        return producerDAO;
    }

    public ProductDAO getProductDAO() {
        return ProductAO;
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }
}
