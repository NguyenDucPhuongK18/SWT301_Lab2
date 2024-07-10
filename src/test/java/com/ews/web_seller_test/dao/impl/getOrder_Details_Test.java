package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class getOrder_Details_Test {

    Order_DetailsDaoImpl orderDetailsDao = new Order_DetailsDaoImpl();
    OrderDaoImpl orderDao = new OrderDaoImpl();
    ProductDaoImpl productDao = new ProductDaoImpl();
    UserDaoImpl userDao = new UserDaoImpl();
    CategoryDaoImpl categoryDao = new CategoryDaoImpl();
    RoleDaoImpl roleDao = new RoleDaoImpl();


    @BeforeEach
    void setup() throws SQLException {
        orderDetailsDao.connection.prepareStatement("TRUNCATE TABLE Order_Details").executeUpdate();
        orderDao.connection.prepareStatement("TRUNCATE TABLE Orders").executeUpdate();
        productDao.connection.prepareStatement("TRUNCATE TABLE Product").executeUpdate();
        userDao.connection.prepareStatement("TRUNCATE TABLE User").executeUpdate();
        categoryDao.connection.prepareStatement("TRUNCATE TABLE Category").executeUpdate();
        roleDao.connection.prepareStatement("TRUNCATE TABLE Role").executeUpdate();
    }

    @Test
    void getOrder_Details() throws SQLException {
        Date checkedDate = new Date();
        Role role = new Role(1, "NameRole1", "Description1", checkedDate, checkedDate);
        roleDao.insertRole(role);
        Category category = new Category(1, "NameCategory1", checkedDate, checkedDate);
        categoryDao.insertCategory(category);
        User user = new User(1, "John Doe", "john@example.com", "1234567890", "123 Main St", "john_doe", "password", "male", null, role, checkedDate, checkedDate);
        userDao.insertUser(user);


        Product product = new Product(1, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate, checkedDate, 5, 100, 1);
        productDao.insertProduct(product);


        Order order = new Order(1, user, 100.0f, "1234567890", "123 Main St", null, 1, 10.0f, 1, 100.0f, checkedDate, checkedDate);
        orderDao.insertOrder(order);


        Order_Details orderDetails1 = new Order_Details(1, order, product, 90.0f, 1, 10.0f, checkedDate, checkedDate);
        orderDetailsDao.insertOrder_Details(orderDetails1);

        Order_Details orderDetails2= orderDetailsDao.getOrder_Details(orderDetails1.getId());

        assertEquals(orderDetails1.getId(), orderDetails2.getId());

    }
}