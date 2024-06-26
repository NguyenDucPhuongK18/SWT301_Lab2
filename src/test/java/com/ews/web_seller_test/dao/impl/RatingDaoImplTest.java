package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.model.Rating;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;
import java.util.List;


class RatingDaoImplTest {
    private RatingDaoImpl ratingDao;

    @BeforeEach
    void setUp() {
        ratingDao = new RatingDaoImpl();
        // Optional: You might want to clear or set up the database state before each test
        // For example, truncate the Rating table
        try {
            ratingDao.connection.prepareStatement("TRUNCATE TABLE Rating").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Database setup failed: " + e.getMessage());
        }
    }

    @Test
    void insertRating() throws SQLException {
        Rating rating = new Rating();
        rating.setContent("Great product!");
        rating.setProduct_id(1);
        rating.setUser_id(1);
        rating.setNumber_starts(5);

        ratingDao.insertRating(rating);

        // Check if the rating was inserted successfully
        List<Rating> ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(1, ratings.size());
        assertEquals("Great product!", ratings.get(0).getContent());
        assertEquals(1, ratings.get(0).getProduct_id());
        assertEquals(1, ratings.get(0).getUser_id());
        assertEquals(5, ratings.get(0).getNumber_starts());
    }

    @Test
    void editRating() throws SQLException {
        Rating rating = new Rating();
        rating.setContent("Good product!");
        rating.setProduct_id(1);
        rating.setUser_id(1);
        rating.setNumber_starts(4);

        ratingDao.insertRating(rating);

        // Retrieve the inserted rating
        List<Rating> ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(1, ratings.size());

        // Modify the rating
        Rating updatedRating = ratings.get(0);
        updatedRating.setContent("Awesome product!");
        updatedRating.setNumber_starts(5);

        ratingDao.editRating(updatedRating);

        // Check if the rating was updated correctly
        Rating retrievedRating = ratingDao.getRating(updatedRating.getId());
        assertNotNull(retrievedRating);
        assertEquals("Awesome product!", retrievedRating.getContent());
        assertEquals(5, retrievedRating.getNumber_starts());
    }

    @Test
    void deleteRating() throws SQLException {
        Rating rating = new Rating();
        rating.setContent("This is a test review");
        rating.setProduct_id(1);
        rating.setUser_id(1);
        rating.setNumber_starts(3);

        ratingDao.insertRating(rating);

        // Retrieve the inserted rating
        List<Rating> ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(1, ratings.size());

        // Delete the rating
        ratingDao.deleteRating(ratings.get(0).getId());

        // Check if the rating was deleted
        ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(0, ratings.size());
    }

    @Test
    void getRating() throws SQLException {
        Rating rating = new Rating();
        rating.setContent("Another test review");
        rating.setProduct_id(1);
        rating.setUser_id(1);
        rating.setNumber_starts(4);

        ratingDao.insertRating(rating);

        // Retrieve the inserted rating
        List<Rating> ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(1, ratings.size());

        // Retrieve the specific rating by ID
        Rating retrievedRating = ratingDao.getRating(ratings.get(0).getId());

        assertNotNull(retrievedRating);
        assertEquals("Another test review", retrievedRating.getContent());
        assertEquals(4, retrievedRating.getNumber_starts());
    }

    @Test
    void getAllRating() throws SQLException {
        ratingDao.insertRating(createRating(1, "First review", 1, 1, 5));
        ratingDao.insertRating(createRating(2, "Second review", 1, 2, 4));
        ratingDao.insertRating(createRating(3, "Third review", 2, 3, 3));

        List<Rating> ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(3, ratings.size());
    }

    private Rating createRating(int id, String content, int productId, int userId, int stars) {
        Rating rating = new Rating();
        rating.setId(id);
        rating.setContent(content);
        rating.setProduct_id(productId);
        rating.setUser_id(userId);
        rating.setNumber_starts(stars);
        return rating;
    }

}
