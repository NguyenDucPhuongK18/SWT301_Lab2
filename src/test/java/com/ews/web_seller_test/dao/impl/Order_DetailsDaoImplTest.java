package com.ews.web_seller_test.dao.impl;


import com.ews.web_seller_test.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


class Order_DetailsDaoImplTest {


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
    void insertOrder_Details() throws SQLException {
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


        List<Order_Details> orderDetailsList = orderDetailsDao.getAllOrder_Details();
        assertNotNull(orderDetailsList);
        assertEquals(1, orderDetailsList.size());
        assertEquals(orderDetails.getId(), orderDetailsList.get(0).getId());
    }


    @Test
    void editOrder_Details() throws SQLException {
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


        orderDetails.setQuantity(2);
        orderDetails.setPrice(180.0f);
        orderDetailsDao.editOrder_Details(orderDetails);


        Order_Details updatedOrderDetails = orderDetailsDao.getOrder_Details(orderDetails.getId());
        assertNotNull(updatedOrderDetails);
        assertEquals(2, updatedOrderDetails.getQuantity());
        assertEquals(180.0, updatedOrderDetails.getPrice());
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


        Order_Details orderDetails = new Order_Details(1, order, product, 90.0f, 1, 10.0f, checkedDate, checkedDate);
        orderDetailsDao.insertOrder_Details(orderDetails);


        List<Order_Details> orderDetailsList = orderDetailsDao.getAllOrder_Details();
        assertNotNull(orderDetailsList);
        assertEquals(orderDetails.getId(), orderDetailsList.get(0).getId());

    }


    @Test
    void getAllOrder_Details() throws SQLException {
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


        Order_Details orderDetails2 = new Order_Details(2, order, product, 90.0f, 2, 10.0f, checkedDate, checkedDate);
        orderDetailsDao.insertOrder_Details(orderDetails2);


        List<Order_Details> orderDetailsList = orderDetailsDao.getAllOrder_Details();
        assertNotNull(orderDetailsList);
        assertEquals(2, orderDetailsList.size());
    }

    @Test
    void orderDetailsListByOrderId() throws SQLException {
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

        List<Order_Details> orderDetailsList = orderDetailsDao.orderDetailsListByOrderId(order.getId());
        System.out.println(orderDetailsList);
        assertNotNull(orderDetailsList);
        assertEquals(1, orderDetailsList.size());
        assertEquals(orderDetails.getId(), orderDetailsList.get(0).getId());
        assertEquals(order.getId(), orderDetailsList.get(0).getOrder().getId());

    }

    @Test
    void getOrder_Details_ReturnsNull() {
        Order_Details orderDetails = orderDetailsDao.getOrder_Details("nonExistentProductName");
        assertNull(orderDetails, "The method should return null for a non-existent product name");
    }
    @Test
    void searchOrder_Details_ReturnsNull() {
        List<Order_Details> orderDetailsList = orderDetailsDao.searchOrder_Details("nonExistentProductName");
        assertNull(orderDetailsList, "The method should return null for a non-existent product name");
    }




    @Test
    void testExceptionHandling() {
        assertThrows(RuntimeException.class, () -> {
            orderDetailsDao.getOrder_Details(-1);
        });
    }


}


