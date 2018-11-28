package com.netcracker.cmstore.model;

import javax.persistence.*;

@Entity
@Table(name = "order")
@SecondaryTable(name = "order_product", pkJoinColumns =
    @PrimaryKeyJoinColumn(name = "order_id", referencedColumnName = "id")
)
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "date")
    private String date;

    @Column(name = "product_id", table = "order_product")
    private String productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
