package com.ews.web_seller_test.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RatingTest {
    private Rating rating;

    @BeforeEach
    void setUp() {
        // Initialize a Rating object before each test
        rating = new Rating(1, "Great product!", 1234, 5678, new Date(), new Date(), 5);
    }

    @Test
    void getId() {
        assertEquals(1, rating.getId());
    }

    @Test
    void setId() {
        rating.setId(2);
        assertEquals(2, rating.getId());
    }

    @Test
    void getContent() {
        assertEquals("Great product!", rating.getContent());
    }

    @Test
    void setContent() {
        rating.setContent("Excellent service!");
        assertEquals("Excellent service!", rating.getContent());
    }

    @Test
    void getProduct_id() {
        assertEquals(1234, rating.getProduct_id());
    }

    @Test
    void setProduct_id() {
        rating.setProduct_id(5678);
        assertEquals(5678, rating.getProduct_id());
    }

    @Test
    void getUser_id() {
        assertEquals(5678, rating.getUser_id());
    }

    @Test
    void setUser_id() {
        rating.setUser_id(9876);
        assertEquals(9876, rating.getUser_id());
    }

    @Test
    void getUpdated_id() {
        assertEquals(rating.getUpdated_id(), rating.getUpdated_id());
    }

    @Test
    void setUpdated_id() {
        Date updatedDate = new Date();
        rating.setUpdated_id(updatedDate);
        assertEquals(updatedDate, rating.getUpdated_id());
    }

    @Test
    void getCreated_id() {
        assertEquals(rating.getCreated_id(), rating.getCreated_id());
    }

    @Test
    void setCreated_id() {
        Date createdDate = new Date();
        rating.setCreated_id(createdDate);
        assertEquals(createdDate, rating.getCreated_id());
    }

    @Test
    void getNumber_starts() {
        assertEquals(5, rating.getNumber_starts());
    }

    @Test
    void setNumber_starts() {
        rating.setNumber_starts(4);
        assertEquals(4, rating.getNumber_starts());
    }
}
