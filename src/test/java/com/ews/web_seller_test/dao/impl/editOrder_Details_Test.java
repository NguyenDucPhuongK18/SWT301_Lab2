package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class editOrder_Details_Test {
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
    void editOrder_Details_valid() throws SQLException {
        Date checkedDate1 = new Date();
        Date checkedDate2 = new Date();

        Role role = new Role(1, "NameRole1", "Description1", checkedDate1, checkedDate1);
        roleDao.insertRole(role);
        Category category = new Category(1, "NameCategory1", checkedDate1, checkedDate1);
        categoryDao.insertCategory(category);
        User user = new User(1, "John Doe", "john@example.com", "1234567890", "123 Main St", "john_doe", "password", "male", null, role, checkedDate1, checkedDate1);
        userDao.insertUser(user);
        Product product1 = new Product(1, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product1);
        Product product2 = new Product(2, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product2);
        Order order1 = new Order(1, user, 100.0f, "1234567890", "123 Main St", null, 1, 10.0f, 1, 100.0f, checkedDate1, checkedDate1);
        orderDao.insertOrder(order1);
        Order_Details orderDetails = new Order_Details(1, order1, product1, 90.0f, 1, 10.0f, checkedDate1, checkedDate1);
        orderDetailsDao.insertOrder_Details(orderDetails);

        orderDetails.setProduct(product2);
        orderDetails.setPrice(100.f);
        orderDetails.setQuantity(2);
        orderDetails.setDiscount(15.0f);
        orderDetails.setUpdated_at(checkedDate2);

        orderDetailsDao.editOrder_Details(orderDetails);

        Order_Details updatedOrderDetails = orderDetailsDao.getOrder_Details(orderDetails.getId());

        assertNotNull(updatedOrderDetails);

        assertEquals(product2.getId(), updatedOrderDetails.getProduct().getId());
        assertEquals(100.0, updatedOrderDetails.getPrice());
        assertEquals(2, updatedOrderDetails.getQuantity());
        assertEquals(15.0f, updatedOrderDetails.getDiscount());
        assertEquals(checkedDate2, updatedOrderDetails.getUpdated_at());
    }
    @Test
    void editOrder_Details_invalidProduct() throws SQLException {
        Date checkedDate1 = new Date();
        Role role = new Role(1, "NameRole1", "Description1", checkedDate1, checkedDate1);
        roleDao.insertRole(role);
        Category category = new Category(1, "NameCategory1", checkedDate1, checkedDate1);
        categoryDao.insertCategory(category);
        User user = new User(1, "John Doe", "john@example.com", "1234567890", "123 Main St", "john_doe", "password", "male", null, role, checkedDate1, checkedDate1);
        userDao.insertUser(user);
        Product product1 = new Product(1, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product1);
        Product product2 = new Product(2, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product2);
        Order order1 = new Order(1, user, 100.0f, "1234567890", "123 Main St", null, 1, 10.0f, 1, 100.0f, checkedDate1, checkedDate1);
        orderDao.insertOrder(order1);
        Order_Details orderDetails = new Order_Details(1, order1, product1, 90.0f, 1, 10.0f, checkedDate1, checkedDate1);
        orderDetailsDao.insertOrder_Details(orderDetails);

        orderDetails.setProduct(null);

        assertThrows(NullPointerException.class, () -> {
            orderDetailsDao.editOrder_Details(orderDetails);
        });
    }
    @Test
    void editOrder_Details_invalidPrice() throws SQLException {
        Date checkedDate1 = new Date();

        Role role = new Role(1, "NameRole1", "Description1", checkedDate1, checkedDate1);
        roleDao.insertRole(role);
        Category category = new Category(1, "NameCategory1", checkedDate1, checkedDate1);
        categoryDao.insertCategory(category);
        User user = new User(1, "John Doe", "john@example.com", "1234567890", "123 Main St", "john_doe", "password", "male", null, role, checkedDate1, checkedDate1);
        userDao.insertUser(user);
        Product product1 = new Product(1, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product1);
        Product product2 = new Product(2, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product2);
        Order order1 = new Order(1, user, 100.0f, "1234567890", "123 Main St", null, 1, 10.0f, 1, 100.0f, checkedDate1, checkedDate1);
        orderDao.insertOrder(order1);
        Order_Details orderDetails = new Order_Details(1, order1, product1, 90.0f, 1, 10.0f, checkedDate1, checkedDate1);
        orderDetailsDao.insertOrder_Details(orderDetails);

        orderDetails.setPrice(-90.0f);

        orderDetailsDao.editOrder_Details(orderDetails);

        Order_Details updatedOrderDetails = orderDetailsDao.getOrder_Details(orderDetails.getId());

        assertNotNull(updatedOrderDetails);
        assertEquals(-90.0f, updatedOrderDetails.getPrice());

    }
    @Test
    void editOrder_Details_Quantity0() throws SQLException {
        Date checkedDate1 = new Date();

        Role role = new Role(1, "NameRole1", "Description1", checkedDate1, checkedDate1);
        roleDao.insertRole(role);
        Category category = new Category(1, "NameCategory1", checkedDate1, checkedDate1);
        categoryDao.insertCategory(category);
        User user = new User(1, "John Doe", "john@example.com", "1234567890", "123 Main St", "john_doe", "password", "male", null, role, checkedDate1, checkedDate1);
        userDao.insertUser(user);
        Product product1 = new Product(1, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product1);
        Product product2 = new Product(2, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product2);
        Order order1 = new Order(1, user, 100.0f, "1234567890", "123 Main St", null, 1, 10.0f, 1, 100.0f, checkedDate1, checkedDate1);
        orderDao.insertOrder(order1);
        Order_Details orderDetails = new Order_Details(1, order1, product1, 90.0f, 1, 10.0f, checkedDate1, checkedDate1);
        orderDetailsDao.insertOrder_Details(orderDetails);

        orderDetails.setQuantity(0);

        orderDetailsDao.editOrder_Details(orderDetails);

        Order_Details updatedOrderDetails = orderDetailsDao.getOrder_Details(orderDetails.getId());

        assertNotNull(updatedOrderDetails);
        assertEquals(0, updatedOrderDetails.getQuantity());

    }
    @Test
    void editOrder_Details_invalidQuantity() throws SQLException {
        Date checkedDate1 = new Date();

        Role role = new Role(1, "NameRole1", "Description1", checkedDate1, checkedDate1);
        roleDao.insertRole(role);
        Category category = new Category(1, "NameCategory1", checkedDate1, checkedDate1);
        categoryDao.insertCategory(category);
        User user = new User(1, "John Doe", "john@example.com", "1234567890", "123 Main St", "john_doe", "password", "male", null, role, checkedDate1, checkedDate1);
        userDao.insertUser(user);
        Product product1 = new Product(1, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product1);
        Product product2 = new Product(2, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product2);
        Order order1 = new Order(1, user, 100.0f, "1234567890", "123 Main St", null, 1, 10.0f, 1, 100.0f, checkedDate1, checkedDate1);
        orderDao.insertOrder(order1);
        Order_Details orderDetails = new Order_Details(1, order1, product1, 90.0f, 1, 10.0f, checkedDate1, checkedDate1);
        orderDetailsDao.insertOrder_Details(orderDetails);

        orderDetails.setQuantity(-1);

        orderDetailsDao.editOrder_Details(orderDetails);

        Order_Details updatedOrderDetails = orderDetailsDao.getOrder_Details(orderDetails.getId());

        assertNotNull(updatedOrderDetails);
        assertEquals(-1, updatedOrderDetails.getQuantity());

    }
    @Test
    void editOrder_Details_invalidDiscount() throws SQLException {
        Date checkedDate1 = new Date();

        Role role = new Role(1, "NameRole1", "Description1", checkedDate1, checkedDate1);
        roleDao.insertRole(role);
        Category category = new Category(1, "NameCategory1", checkedDate1, checkedDate1);
        categoryDao.insertCategory(category);
        User user = new User(1, "John Doe", "john@example.com", "1234567890", "123 Main St", "john_doe", "password", "male", null, role, checkedDate1, checkedDate1);
        userDao.insertUser(user);
        Product product1 = new Product(1, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product1);
        Product product2 = new Product(2, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product2);
        Order order1 = new Order(1, user, 100.0f, "1234567890", "123 Main St", null, 1, 10.0f, 1, 100.0f, checkedDate1, checkedDate1);
        orderDao.insertOrder(order1);
        Order_Details orderDetails = new Order_Details(1, order1, product1, 90.0f, 1, 10.0f, checkedDate1, checkedDate1);
        orderDetailsDao.insertOrder_Details(orderDetails);

        orderDetails.setDiscount(-1.0f);

        orderDetailsDao.editOrder_Details(orderDetails);

        Order_Details updatedOrderDetails = orderDetailsDao.getOrder_Details(orderDetails.getId());

        assertNotNull(updatedOrderDetails);
        assertEquals(-1.0f, updatedOrderDetails.getDiscount());

    }
    @Test
    void editOrder_Details_invalidDate() throws SQLException {
        Date checkedDate1 = new Date();

        Role role = new Role(1, "NameRole1", "Description1", checkedDate1, checkedDate1);
        roleDao.insertRole(role);
        Category category = new Category(1, "NameCategory1", checkedDate1, checkedDate1);
        categoryDao.insertCategory(category);
        User user = new User(1, "John Doe", "john@example.com", "1234567890", "123 Main St", "john_doe", "password", "male", null, role, checkedDate1, checkedDate1);
        userDao.insertUser(user);
        Product product1 = new Product(1, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product1);
        Product product2 = new Product(2, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product2);
        Order order1 = new Order(1, user, 100.0f, "1234567890", "123 Main St", null, 1, 10.0f, 1, 100.0f, checkedDate1, checkedDate1);
        orderDao.insertOrder(order1);
        Order_Details orderDetails = new Order_Details(1, order1, product1, 90.0f, 1, 10.0f, checkedDate1, checkedDate1);
        orderDetailsDao.insertOrder_Details(orderDetails);

        orderDetails.setUpdated_at(null);

        orderDetailsDao.editOrder_Details(orderDetails);

        Order_Details updatedOrderDetails = orderDetailsDao.getOrder_Details(orderDetails.getId());

        assertNotNull(updatedOrderDetails);
        assertEquals(null, updatedOrderDetails.getUpdated_at());

    }
    @Test
    void editOrder_Details_DateFuture() throws SQLException {
        Date checkedDate1 = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 11);
        Date checkedDateInvalid = calendar.getTime();

        Role role = new Role(1, "NameRole1", "Description1", checkedDate1, checkedDate1);
        roleDao.insertRole(role);
        Category category = new Category(1, "NameCategory1", checkedDate1, checkedDate1);
        categoryDao.insertCategory(category);
        User user = new User(1, "John Doe", "john@example.com", "1234567890", "123 Main St", "john_doe", "password", "male", null, role, checkedDate1, checkedDate1);
        userDao.insertUser(user);
        Product product1 = new Product(1, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product1);
        Product product2 = new Product(2, category, "Test Product", 100.0f, 10.0f, null, "Description", checkedDate1, checkedDate1, 5, 100, 1);
        productDao.insertProduct(product2);
        Order order1 = new Order(1, user, 100.0f, "1234567890", "123 Main St", null, 1, 10.0f, 1, 100.0f, checkedDate1, checkedDate1);
        orderDao.insertOrder(order1);
        Order_Details orderDetails = new Order_Details(1, order1, product1, 90.0f, 1, 10.0f, checkedDate1, checkedDate1);
        orderDetailsDao.insertOrder_Details(orderDetails);

        orderDetails.setUpdated_at(checkedDateInvalid);

        orderDetailsDao.editOrder_Details(orderDetails);

        Order_Details updatedOrderDetails = orderDetailsDao.getOrder_Details(orderDetails.getId());

        assertNotNull(updatedOrderDetails);
        assertEquals(checkedDateInvalid, updatedOrderDetails.getUpdated_at());

    }
}