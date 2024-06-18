package com.ews.web_seller_test.service;

import com.ews.web_seller_test.model.User;

import java.util.List;

public interface UserService {
    void insertUser(User user);
    void editUser(User user);
    void deleteUser(int id);
    User getUser(String username);
    User getUser(int id);
    User loginUser(String username, String password);
    boolean registerUser(String username,String password, String email, String full_name, String phone, String address, String gender);
    List<User> getAllUser();
    List<User> searchUser(String keyword);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
}
