package com.ews.web_seller_test.model;

import java.io.Serializable;
import java.util.Date;

public class Order_Details implements Serializable {

    private int id;
    private Order order;
    private Product product;
    private float price;
    private int quantity;
    private float discount;
    private Date created_at;
    private Date updated_at;

    public Order_Details() {
    }

    public Order_Details(Order order, Product product, float price, int quantity, float discount, Date created_at, Date updated_at) {
        this.order = order;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
