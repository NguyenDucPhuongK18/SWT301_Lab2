package com.ews.web_seller_test.model;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private int id;
    private String role_name;
    private String description;
    private Date created_at;
    private Date updated_at;

    public Role(int id, String role_name, String description, Date created_at, Date updated_at) {
        this.id = id;
        this.role_name = role_name;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Role() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
