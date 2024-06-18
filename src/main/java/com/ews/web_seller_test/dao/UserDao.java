package com.ews.web_seller_test.dao;

import com.ews.web_seller_test.model.User;

import java.util.List;


public interface UserDao {
    void insertUser(User user);

    void editUser(User user);

    void deleteUser(int id);

    User getUser(String username);

    User getUser(int id);

    List<User> getAllUser();

    List<User> searchUser(String username);

    boolean checkExistEmail(String email);

    boolean checkExistUsername(String username);
}
