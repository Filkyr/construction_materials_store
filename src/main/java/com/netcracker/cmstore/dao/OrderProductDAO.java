package com.netcracker.cmstore.dao;

import com.netcracker.cmstore.model.OrderProduct;

import java.util.List;

public interface OrderProductDAO {
    void addOrderProduct(OrderProduct orderProduct);

    void removeOrderProduct(int orderId, int productId);

    void updateOrderProduct(OrderProduct orderProduct);

    List<OrderProduct> getOrderProducts();

    OrderProduct getOrderProductById(int orderProductId);
}
