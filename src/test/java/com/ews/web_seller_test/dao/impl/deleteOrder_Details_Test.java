package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class deleteOrder_Details_Test {

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
    void deleteOrder_Details() throws SQLException {
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

        Order_Details orderDetails = new Order_Details(1, order, product, 90.0f, 1, 10.0f, checkedDate, checkedDate);
        orderDetailsDao.insertOrder_Details(orderDetails);

        orderDetailsDao.deleteOrder_Details(orderDetails.getId());

        List<Order_Details> orderDetailsList = orderDetailsDao.orderDetailsListByOrderId(order.getId());
        assertNotNull(orderDetailsList);
        assertEquals(0, orderDetailsList.size());
    }

    @Test
    void deleteNonExistentOrder_Details() throws SQLException {
        orderDetailsDao.deleteOrder_Details(9999);

        List<Order_Details> orderDetailsList = orderDetailsDao.orderDetailsListByOrderId(9999);
        assertNotNull(orderDetailsList);
        assertEquals(0, orderDetailsList.size());
    }

    @Test
    void deleteOrder_DetailsWithMultipleEntries() throws SQLException {
        Date checkedDate = new Date();
        Role role = new Role(1, "NameRole1", "Description1", checkedDate, checkedDate);
        roleDao.insertRole(role);
        Category category = new Category(1, "NameCategory1", checkedDate, checkedDate);
        categoryDao.insertCategory(category);
        User user = new User(1, "John Doe", "john@example.com", "1234567890", "123 Main St", "john_doe", "password", "male", null, role, checkedDate, checkedDate);
        userDao.insertUser(user);

        Product product1 = new Product(1, category, "Test Product 1", 100.0f, 10.0f, null, "Description", checkedDate, checkedDate, 5, 100, 1);
        Product product2 = new Product(2, category, "Test Product 2", 200.0f, 20.0f, null, "Description", checkedDate, checkedDate, 5, 200, 2);
        productDao.insertProduct(product1);
        productDao.insertProduct(product2);

        Order order = new Order(1, user, 300.0f, "1234567890", "123 Main St", null, 1, 30.0f, 1, 300.0f, checkedDate, checkedDate);
        orderDao.insertOrder(order);

        Order_Details orderDetails1 = new Order_Details(1, order, product1, 90.0f, 1, 10.0f, checkedDate, checkedDate);
        Order_Details orderDetails2 = new Order_Details(2, order, product1, 90.0f, 1, 20.0f, checkedDate, checkedDate);
        orderDetailsDao.insertOrder_Details(orderDetails1);
        orderDetailsDao.insertOrder_Details(orderDetails2);

        orderDetailsDao.deleteOrder_Details(orderDetails1.getId());

        List<Order_Details> orderDetailsList = orderDetailsDao.orderDetailsListByOrderId(order.getId());
        assertNotNull(orderDetailsList);
        assertEquals(1, orderDetailsList.size());
        assertEquals(orderDetails2.getId(), orderDetailsList.get(0).getId());
    }
}