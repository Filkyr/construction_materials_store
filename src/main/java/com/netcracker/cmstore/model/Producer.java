package com.netcracker.cmstore.model;

import javax.persistence.*;

@Entity
@Table(name = "producer")
public class Producer {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int producerId;

    @Column(name = "brand_name")
    private String brand_name;

    @Column(name = "description")
    private String description;

    @Column(name = "logo")
    private String logo;

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
