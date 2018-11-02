package com.netcracker.cmstore.dao;

import com.netcracker.cmstore.model.OrderProduct;

public interface OrderProductDAO {
    void addOrderProduct(OrderProduct orderProduct);

    void removeOrderProduct(int orderId, int productId);

}
