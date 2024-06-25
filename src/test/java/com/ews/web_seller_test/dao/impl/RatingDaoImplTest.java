package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.model.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RatingDaoImplTest {
    RatingDaoImpl ratingDao = new RatingDaoImpl();

    @BeforeEach
    void setUp() throws SQLException {
        ratingDao.con.prepareStatement("TRUNCATE TABLE Rating").executeUpdate();
    }

    @Test
    void insertRating() throws SQLException {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        Rating rating = new Rating(1, "Great product!", 1, 1, currentTimestamp, currentTimestamp, 5);
        ratingDao.insertRating(rating);
        List<Rating> ratings = ratingDao.getAllRating();

        assertNotNull(ratings);
        assertEquals(1, ratings.size());
        assertEquals(1, ratings.get(0).getId());
        assertEquals("Great product!", ratings.get(0).getContent());
        assertEquals(1, ratings.get(0).getProduct_id());
        assertEquals(1, ratings.get(0).getUser_id());
        assertEquals(5, ratings.get(0).getNumber_starts());
        assertEquals(currentTimestamp, ratings.get(0).getCreated_id()); // Adjust this line
        assertEquals(currentTimestamp, ratings.get(0).getUpdated_id()); // Adjust this line
    }

    @Test
    void editRating() throws SQLException {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        Rating rating = new Rating(1, "Great product!", 1, 1, currentTimestamp, currentTimestamp, 5);
        ratingDao.insertRating(rating);
        List<Rating> ratings = ratingDao.getAllRating();

        Rating updated = ratings.get(0);
        assertNotNull(ratings);
        assertEquals(1, ratings.size());
        assertEquals(1, updated.getId());
        assertEquals("Great product!", updated.getContent());

        updated.setContent("Updated content");
        updated.setNumber_starts(4);

        ratingDao.editRating(updated);

        updated = ratingDao.getRating(updated.getId());
        assertEquals("Updated content", updated.getContent());
        assertEquals(4, updated.getNumber_starts());
    }

    @Test
    void deleteRating() throws SQLException {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        ratingDao.con.prepareStatement("TRUNCATE TABLE Rating").executeUpdate();
        Rating rating = new Rating(1, "Great product!", 1, 1, currentTimestamp, currentTimestamp, 5);
        ratingDao.insertRating(rating);

        List<Rating> ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(1, ratings.size());
        assertEquals(1, ratings.get(0).getId());
        assertEquals("Great product!", ratings.get(0).getContent());

        ratingDao.deleteRating(1);
        ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(0, ratings.size());
    }

    @Test
    void getRating() throws SQLException {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        Rating rating = new Rating(1, "Great product!", 1, 1, currentTimestamp, currentTimestamp, 5);
        ratingDao.insertRating(rating);

        Rating retrieved = ratingDao.getRating(1);
        assertNotNull(retrieved);
        assertEquals(1, retrieved.getId());
        assertEquals("Great product!", retrieved.getContent());
        assertEquals(1, retrieved.getProduct_id());
        assertEquals(1, retrieved.getUser_id());
        assertEquals(5, retrieved.getNumber_starts());
    }

    @Test
    void getAllRating() throws SQLException {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        ratingDao.insertRating(new Rating(1, "Great product!", 1, 1, currentTimestamp, currentTimestamp, 5));
        ratingDao.insertRating(new Rating(2, "Not bad", 2, 2, currentTimestamp, currentTimestamp, 3));

        List<Rating> ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(2, ratings.size());

        Rating rating1 = ratings.get(0);
        assertEquals(1, rating1.getId());
        assertEquals("Great product!", rating1.getContent());
        assertEquals(1, rating1.getProduct_id());
        assertEquals(1, rating1.getUser_id());
        assertEquals(5, rating1.getNumber_starts());

        Rating rating2 = ratings.get(1);
        assertEquals(2, rating2.getId());
        assertEquals("Not bad", rating2.getContent());
        assertEquals(2, rating2.getProduct_id());
        assertEquals(2, rating2.getUser_id());
        assertEquals(3, rating2.getNumber_starts());
    }
}
