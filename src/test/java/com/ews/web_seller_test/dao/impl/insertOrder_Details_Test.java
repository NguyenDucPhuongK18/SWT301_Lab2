package com.ews.web_seller_test.dao.impl;


import com.ews.web_seller_test.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


class insertOrder_Details_Test {


    Order_DetailsDaoImpl orderDetailsDao = new Order_DetailsDaoImpl();
    OrderDaoImpl orderDao = new OrderDaoImpl();
    ProductDaoImpl productDao = new ProductDaoImpl();
    UserDaoImpl userDao = new UserDaoImpl();
    CategoryDaoImpl categoryDao = new CategoryDaoImpl();
    RoleDaoImpl roleDao = new RoleDaoImpl();
    Date checkedDate = new Date();

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
    void insertOrder_DetailsTest_valid() throws SQLException {
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
        List<Order_Details> orderDetailsList = orderDetailsDao.getAllOrder_Details();
        assertEquals(1, orderDetailsList.size());

    }
    @Test
    void insertOrder_DetailsTest_invalidOrder() throws SQLException {
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

        Order_Details orderDetails2 = new Order_Details(1, null, product, 90.0f, 1, 10.0f, checkedDate, checkedDate);

        assertThrows(NullPointerException.class, () -> {
            orderDetailsDao.insertOrder_Details(orderDetails2);
        });
    }
    @Test
    void insertOrder_DetailsTest_invalidProduct() throws SQLException {
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

        Order_Details orderDetails3 = new Order_Details(1, order, null, 90.0f, 1, 10.0f, checkedDate, checkedDate);

        assertThrows(NullPointerException.class, () -> {
            orderDetailsDao.insertOrder_Details(orderDetails3);
        });
    }
    @Test
    void insertOrder_DetailsTest_invalidPrice() throws SQLException {
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

        Order_Details orderDetails4 = new Order_Details(1, order, product, -90.0f, 1, 10.0f, checkedDate, checkedDate);


        orderDetailsDao.insertOrder_Details(orderDetails4);
        List<Order_Details> orderDetailsList = orderDetailsDao.getAllOrder_Details();
        assertEquals(0, orderDetailsList.size());
    }
    @Test
    void insertOrder_DetailsTest_invalidQuantityB() throws SQLException {
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

        Order_Details orderDetails5 = new Order_Details(1, order, product, 90.0f, 0, 10.0f, checkedDate, checkedDate);


        orderDetailsDao.insertOrder_Details(orderDetails5);
        List<Order_Details> orderDetailsList = orderDetailsDao.getAllOrder_Details();
        assertEquals(0, orderDetailsList.size());
    }
    @Test
    void insertOrder_DetailsTest_invalidQuantityA() throws SQLException {
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

        Order_Details orderDetails5 = new Order_Details(1, order, product, 90.0f, -1, 10.0f, checkedDate, checkedDate);


        orderDetailsDao.insertOrder_Details(orderDetails5);
        List<Order_Details> orderDetailsList = orderDetailsDao.getAllOrder_Details();
        assertEquals(0, orderDetailsList.size());
    }
    @Test
    void insertOrder_DetailsTest_invalidDiscount() throws SQLException {
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

        Order_Details orderDetails5 = new Order_Details(1, order, product, 90.0f, 1, -10.0f, checkedDate, checkedDate);


        orderDetailsDao.insertOrder_Details(orderDetails5);
        List<Order_Details> orderDetailsList = orderDetailsDao.getAllOrder_Details();
        assertEquals(0, orderDetailsList.size());
    }
    @Test
    void insertOrder_DetailsTest_invalidDateNull() throws SQLException {
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

        Order_Details orderDetails5 = new Order_Details(1, order, product, 90.0f, 1, 10.0f, null, checkedDate);


        orderDetailsDao.insertOrder_Details(orderDetails5);
        List<Order_Details> orderDetailsList = orderDetailsDao.getAllOrder_Details();
        assertEquals(0, orderDetailsList.size());
    }
    @Test
    void insertOrder_DetailsTest_invalidDateFuture() throws SQLException {
        Date checkedDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 11);
        Date checkedDateInvalid = calendar.getTime();

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

        Order_Details orderDetails5 = new Order_Details(1, order, product, 90.0f, 1, 10.0f, checkedDateInvalid, checkedDate);


        orderDetailsDao.insertOrder_Details(orderDetails5);
        List<Order_Details> orderDetailsList = orderDetailsDao.getAllOrder_Details();
        assertEquals(0, orderDetailsList.size());
    }

}


