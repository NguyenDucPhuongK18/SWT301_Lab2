package com.ews.web_seller_test.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int id;
    private String full_name;
    private String email;
    private String phone;
    private String address;
    private String username;
    private String password;
    private String gender;
    private String avatar;
    private Role role;
    private Date created_at;
    private Date updated_at;

    public User(String full_name, String email, String phone, String address, String username, String password, String gender, String avatar, Role role, Date created_at, Date updated_at) {
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.avatar = avatar;
        this.role = role;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public User(int id, String full_name, String email, String phone, String address, String username, String password, String gender, String avatar, Role role, Date created_at, Date updated_at) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.avatar = avatar;
        this.role = role;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public User(String full_name, String email, String phone, String address, String username, String password, String gender, String avatar, Role role) {
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.avatar = avatar;
        this.role = role;
    }

    public User(String full_name, String email, String phone, String address, String username, String password, String gender, String avatar) {
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.avatar = avatar;
    }

    public User(String full_name, String email, String phone, String address, String username, String password, String gender) {
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.gender = gender;
    }

    public User() {

    }

    public User(int i, String testUser, String password) {
        this.id = i;
        this.full_name = testUser;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                ", role=" + role +
                '}';
    }
}
