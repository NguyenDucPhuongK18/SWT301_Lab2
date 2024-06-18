package com.ews.web_seller_test.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int id;
    private User buyer;
    private float price;
    private String phone;
    private String address;
    private String note;
    private int status;
    private float total_discount;
    private int total_quantity;
    private float total_price;
    private Date created_at;
    private Date updated_at;

    public Order() {
    }

    public Order(User buyer, float price, String phone, String address, String note, int status, float total_discount, int total_quantity, float total_price, Date created_at, Date updated_at) {
        this.buyer = buyer;
        this.price = price;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.status = status;
        this.total_discount = total_discount;
        this.total_quantity = total_quantity;
        this.total_price = total_price;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getTotal_discount() {
        return total_discount;
    }

    public void setTotal_discount(float total_discount) {
        this.total_discount = total_discount;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
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
