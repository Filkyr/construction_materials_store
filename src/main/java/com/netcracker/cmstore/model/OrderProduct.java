package com.netcracker.cmstore.model;

import javax.persistence.*;

@Entity
@Table(name = "order_product")
public class OrderProduct {

    @Id
    @Column(name = "order_id")
    private int orderId;

    @Id
    @Column(name = "product_id")
    private int productId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}