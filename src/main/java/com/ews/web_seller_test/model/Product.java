package com.ews.web_seller_test.model;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {

    private int id;
    private Category category;
    private String name;
    private float price;
    private float discount;
    private String image;
    private String description;
    private Date created_at;
    private Date updated_at;
    private int total_rating;
    private int total_starts;
    private int status;

    public Product(int i, String product1) {
        this.id = i;
        this.name = product1;
    }

    public Product(Category category, String name, float price, float discount, String image, String description, int total_rating, int total_starts, int status) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.description = description;
        this.total_rating = total_rating;
        this.total_starts = total_starts;
        this.status = status;
    }


    public Product(int id, Category category, String name, float price, float discount, String image, String description, Date created_at, Date updated_at, int total_rating, int total_starts, int status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.total_rating = total_rating;
        this.total_starts = total_starts;
        this.status = status;
    }

    public Product(Category category, String name, float price, float discount, String image, String description, Date created_at, Date updated_at, int total_rating, int total_starts, int status) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.total_rating = total_rating;
        this.total_starts = total_starts;
        this.status = status;
    }

    public Product(int i, String testProduct, float v) {
    }

    public Product(int i, String testProduct, String description, float v, Category testCategory) {
        this.id = i;
        this.name = testProduct;
        this.description = description;
        this.price = v;
        this.category = testCategory;
    }

    public Product() {

    }

    public Product(int i, String product1, float v, float v1, String description1) {
        this.id = i;
        this.name = product1;
        this.price = v;
        this.discount = v1;
        this.image = description1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(int total_rating) {
        this.total_rating = total_rating;
    }

    public int getTotal_starts() {
        return total_starts;
    }

    public void setTotal_starts(int total_starts) {
        this.total_starts = total_starts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", total_rating=" + total_rating +
                ", total_starts=" + total_starts +
                '}';
    }
}
