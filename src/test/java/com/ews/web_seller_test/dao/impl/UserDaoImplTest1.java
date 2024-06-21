package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.model.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest1 {
    @Test
    void getAllUser() {
        var userDao = new UserDaoImpl();
        userDao.insertUser(new User("John Doe", "john.doe@example.com", "1234567890", "123 Main St", "johndoe", "password123", "Male", "avatar1.png", null, new Date(), new Date()));
        var allUser = userDao.getAllUser();
        assertNotNull(allUser);
        assertEquals(1, allUser.size());

    }
}