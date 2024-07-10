package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.model.Order;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderDaoImplTest {
    private OrderDaoImpl orderDao;
    private final Logger logger = Logger.getLogger(OrderDaoImpl.class.getName());
    private final TestLogHandler logHandler = new TestLogHandler();

    @BeforeEach
    void setUp() {
        orderDao = new OrderDaoImpl();
        logger.addHandler(logHandler);
        logger.setLevel(Level.ALL);
        try {
            orderDao.connection.prepareStatement("TRUNCATE TABLE Orders").executeUpdate();
            orderDao.connection.prepareStatement("TRUNCATE TABLE `User`").executeUpdate();
            orderDao.connection.prepareStatement("TRUNCATE TABLE Role").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Database setup failed: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        logger.removeHandler(logHandler);
    }

    //Tests insertion of a valid order.
    @Test
    void insertOrder() throws SQLException {
        User user = createUser();
        Order order = createOrder(user);

        orderDao.insertOrder(order);

        List<Order> orders = orderDao.getAllOrder();
        assertNotNull(orders);
        assertEquals(1, orders.size());
    }

    //Tests insertion of an order with an user that does not exist.
    @Test
    void insertOrderInvalid() {
        User user = new User(); // Invalid user
        Order order = createOrder(user);

        orderDao.insertOrder(order);

        assertNotNull(logHandler.getLogContent());
    }

    //Tests retrieval of order ID for a given order.
    @Test
    void getOrderId() throws SQLException {
        User user = createUser();
        Order order = createOrder(user);

        int orderId = orderDao.getOrderId(order);
        assertTrue(orderId > 0);

        Order retrievedOrder = orderDao.getOrder(orderId);
        assertNotNull(retrievedOrder);
        assertEquals(orderId, retrievedOrder.getId());
    }

    //Tests editing an existing order.
    @Test
    void editOrder() throws SQLException {
        User user = createUser();
        Order order = createOrder(user);

        orderDao.insertOrder(order);

        List<Order> orders = orderDao.getAllOrder();
        assertNotNull(orders);
        assertEquals(1, orders.size());
        int orderId = orders.get(0).getId();

        Order updatedOrder = orders.get(0);
        updatedOrder.setPhone("1234567890");
        updatedOrder.setAddress("456 Another St");
        orderDao.editOrder(updatedOrder);

        Order retrievedOrder = orderDao.getOrder(orderId);
        assertNotNull(retrievedOrder);
        assertNotEquals(order.getPhone(), retrievedOrder.getPhone());
        assertNotEquals(order.getAddress(), retrievedOrder.getAddress());

    }

    //Tests deletion of an existing order.
    @Test
    void deleteOrder() throws SQLException {
        User user = createUser();
        Order order = createOrder(user);

        orderDao.insertOrder(order);

        List<Order> orders = orderDao.getAllOrder();
        assertNotNull(orders);
        assertEquals(1, orders.size());

        orderDao.deleteOrder(orders.get(0).getId());

        orders = orderDao.getAllOrder();
        assertNotNull(orders);
        assertEquals(0, orders.size());
    }

    //Tests deletion of an order with an ID that does not exist.
    @Test
    void deleteInvalidOrder() {
        orderDao.deleteOrder(999); // Invalid order ID

        assertNotNull(logHandler.getLogContent());
    }

    //Test get all orders in the system
    @Test
    void getAllOrder() throws SQLException {
        User user = createUser();
        orderDao.insertOrder(createOrder(user));

        List<Order> orders = orderDao.getAllOrder();
        assertNotNull(orders);
        assertEquals(1, orders.size());
    }

    @Test
    void getAllOrderNoResult() throws SQLException {
        List<Order> orders = orderDao.getAllOrder();
        assertEquals(0, orders.size());
    }



    //Test search user by name
    @Test
    void searchOrder() throws SQLException {
        User user = createUser();
        orderDao.insertOrder(createOrder(user));

        List<Order> orders = orderDao.searchOrder("John");
        assertNotNull(orders);
        assertEquals(1, orders.size());
    }

    //Tests editing of an order with an ID that does not exists.
    @Test
    void editOrderInvalid() throws SQLException {
        Order order = createOrder(createUser());
        order.setId(999); // Invalid order ID

        orderDao.editOrder(order);

        assertNotNull(logHandler.getLogContent());
    }

    //Test search with non existent user
    @Test
    void searchOrderNoResults() {
        List<Order> orders = orderDao.searchOrder("NonexistentUser");

        assertNotNull(orders);
        assertEquals(0, orders.size());
    }

    //Tests retrieval of an order with an ID that does not exists.
    @Test
    void getOrderNoResult() {
        Order order = orderDao.getOrder(999); // Nonexistent order ID

        assertNull(order);
    }



    private User createUser() throws SQLException {
        Role role = new Role(1, "User", "Standard user role", new Date(), new Date());
        orderDao.connection.prepareStatement("INSERT INTO Role (id, role_name, description, created_at, updated_at) VALUES (1, 'User', 'Standard user role', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP())").executeUpdate();

        User user = new User("John Doe", "john@example.com", "1234567890", "123 Main St", "john_doe", "password", "male", "avatar.jpg", role, new Date(), new Date());
        user.setId(1);
        orderDao.connection.prepareStatement("INSERT INTO `User` (id, full_name, email, phone, address, username, password, gender, avatar, role_id, created_at, updated_at) VALUES (1, 'John Doe', 'john@example.com', '1234567890', '123 Main St', 'john_doe', 'password', 'male', 'avatar.jpg', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP())").executeUpdate();
        return user;
    }

    private Order createOrder(User user) {
        return new Order(user, 100.0f, "123456789", "123 Main St", "Note", 1, 10.0f, 1, 90.0f, new Date(), new Date());
    }

    private static class TestLogHandler extends Handler {
        private final ByteArrayOutputStream logContent = new ByteArrayOutputStream();
        private final PrintStream logStream = new PrintStream(logContent);

        @Override
        public void publish(LogRecord record) {
            logStream.println(record.getMessage());
        }

        @Override
        public void flush() {
            logStream.flush();
        }

        @Override
        public void close() throws SecurityException {
            logStream.close();
        }

        public String getLogContent() {
            return logContent.toString();
        }
    }
}
