package com.ews.web_seller_test.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
        // Initialize an Order object before each test
        User buyer = new User("John Doe", "john.doe@example.com", "1234567890", "Sample Address", "johndoe", "password", "male");
        order = new Order(buyer, 100.0f, "1234567890", "Sample Address", "Sample Note", 1, 5.0f, 2, 95.0f, new Date(), new Date());
        order.setId(1); // Set ID explicitly for testing
    }

    @Test
    void getId() {
        assertEquals(1, order.getId());
    }

    @Test
    void setId() {
        order.setId(2);
        assertEquals(2, order.getId());
    }

    @Test
    void getBuyer() {
        User buyer = order.getBuyer();
        assertEquals("John Doe", buyer.getFull_name());
        assertEquals("john.doe@example.com", buyer.getEmail());
        assertEquals("1234567890", buyer.getPhone());
        assertEquals("Sample Address", buyer.getAddress());
        assertEquals("johndoe", buyer.getUsername());
        assertEquals("password", buyer.getPassword());
        assertEquals("male", buyer.getGender());
    }

    @Test
    void setBuyer() {
        User newBuyer = new User("Jane Smith", "jane.smith@example.com", "9876543210", "New Address", "janesmith", "password123", "female");
        order.setBuyer(newBuyer);
        assertEquals("Jane Smith", order.getBuyer().getFull_name());
        assertEquals("jane.smith@example.com", order.getBuyer().getEmail());
        assertEquals("9876543210", order.getBuyer().getPhone());
        assertEquals("New Address", order.getBuyer().getAddress());
        assertEquals("janesmith", order.getBuyer().getUsername());
        assertEquals("password123", order.getBuyer().getPassword());
        assertEquals("female", order.getBuyer().getGender());
    }

    @Test
    void getPrice() {
        assertEquals(100.0f, order.getPrice());
    }

    @Test
    void setPrice() {
        order.setPrice(150.0f);
        assertEquals(150.0f, order.getPrice());
    }

    @Test
    void getPhone() {
        assertEquals("1234567890", order.getPhone());
    }

    @Test
    void setPhone() {
        order.setPhone("9876543210");
        assertEquals("9876543210", order.getPhone());
    }

    @Test
    void getAddress() {
        assertEquals("Sample Address", order.getAddress());
    }

    @Test
    void setAddress() {
        order.setAddress("New Address");
        assertEquals("New Address", order.getAddress());
    }

    @Test
    void getNote() {
        assertEquals("Sample Note", order.getNote());
    }

    @Test
    void setNote() {
        order.setNote("New Note");
        assertEquals("New Note", order.getNote());
    }

    @Test
    void getStatus() {
        assertEquals(1, order.getStatus());
    }

    @Test
    void setStatus() {
        order.setStatus(2);
        assertEquals(2, order.getStatus());
    }

    @Test
    void getTotal_discount() {
        assertEquals(5.0f, order.getTotal_discount());
    }

    @Test
    void setTotal_discount() {
        order.setTotal_discount(8.0f);
        assertEquals(8.0f, order.getTotal_discount());
    }

    @Test
    void getTotal_quantity() {
        assertEquals(2, order.getTotal_quantity());
    }

    @Test
    void setTotal_quantity() {
        order.setTotal_quantity(3);
        assertEquals(3, order.getTotal_quantity());
    }

    @Test
    void getTotal_price() {
        assertEquals(95.0f, order.getTotal_price());
    }

    @Test
    void setTotal_price() {
        order.setTotal_price(120.0f);
        assertEquals(120.0f, order.getTotal_price());
    }

    @Test
    void getCreated_at() {
        assertNotNull(order.getCreated_at());
    }

    @Test
    void setCreated_at() {
        Date createdDate = new Date();
        order.setCreated_at(createdDate);
        assertEquals(createdDate, order.getCreated_at());
    }

    @Test
    void getUpdated_at() {
        assertNotNull(order.getUpdated_at());
    }

    @Test
    void setUpdated_at() {
        Date updatedDate = new Date();
        order.setUpdated_at(updatedDate);
        assertEquals(updatedDate, order.getUpdated_at());
    }

    @Test
    void testToString() {
        String expectedToString = "Order[id=1, buyer=User{id=0, full_name='John Doe', email='john.doe@example.com', phone='1234567890', address='Sample Address', username='johndoe', password='password', gender='male', avatar='null', role=null}, price=100.0, phone='1234567890', address='Sample Address', note='Sample Note', status=1, total_discount=5.0, total_quantity=2, total_price=95.0, created_at=" + order.getCreated_at() + ", updated_at=" + order.getUpdated_at() + "]";
        assertEquals(expectedToString, order.toString());
    }
}
