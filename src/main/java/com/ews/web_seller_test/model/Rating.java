package com.ews.web_seller_test.model;

import java.io.Serializable;
import java.util.Date;

public class Rating implements Serializable {
    private int id;
    private String content;
    private int product_id;
    private int user_id;
    private Date updated_id;
    private Date created_id;
    private int number_starts;

    public Rating(int id, String content, int product_id, int user_id, Date updated_id, Date created_id, int number_starts) {
        this.id = id;
        this.content = content;
        this.product_id = product_id;
        this.user_id = user_id;
        this.updated_id = updated_id;
        this.created_id = created_id;
        this.number_starts = number_starts;
    }

    public Rating() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getUpdated_id() {
        return updated_id;
    }

    public void setUpdated_id(Date updated_id) {
        this.updated_id = updated_id;
    }

    public Date getCreated_id() {
        return created_id;
    }

    public void setCreated_id(Date created_id) {
        this.created_id = created_id;
    }

    public int getNumber_starts() {
        return number_starts;
    }

    public void setNumber_starts(int number_starts) {
        this.number_starts = number_starts;
    }
}
