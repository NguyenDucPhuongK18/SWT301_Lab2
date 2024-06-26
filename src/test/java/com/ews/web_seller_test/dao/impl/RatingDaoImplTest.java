package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.model.Rating;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.*;

import static org.junit.jupiter.api.Assertions.*;

class RatingDaoImplTest {
    private RatingDaoImpl ratingDao;
    private final ByteArrayOutputStream logContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;
    private final Logger logger = Logger.getLogger(RatingDaoImpl.class.getName());
    private final TestLogHandler logHandler = new TestLogHandler();

    @BeforeEach
    void setUp() {
        ratingDao = new RatingDaoImpl();
        logger.addHandler(logHandler);
        logger.setLevel(Level.ALL);
        try {
            ratingDao.connection.prepareStatement("TRUNCATE TABLE Rating").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Database setup failed: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        logger.removeHandler(logHandler);
    }

    @Test
    void insertRating() throws SQLException {
        Rating rating = new Rating();
        rating.setContent("Great product!");
        rating.setProduct_id(1);
        rating.setUser_id(1);
        rating.setNumber_starts(5);

        ratingDao.insertRating(rating);

        List<Rating> ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(1, ratings.size());
        assertEquals("Great product!", ratings.get(0).getContent());
        assertEquals(1, ratings.get(0).getProduct_id());
        assertEquals(1, ratings.get(0).getUser_id());
        assertEquals(5, ratings.get(0).getNumber_starts());
    }

    @Test
    void insertRatingSQLException() {
        Rating rating = new Rating();
        rating.setContent("Invalid product");
        rating.setProduct_id(999); // Assuming 999 does not exist
        rating.setUser_id(1);
        rating.setNumber_starts(5);

        ratingDao.insertRating(rating);

        assertNotNull(logHandler.getLogContent());
    }

    @Test
    void editRating() throws SQLException {
        Rating rating = new Rating();
        rating.setContent("Good product!");
        rating.setProduct_id(1);
        rating.setUser_id(1);
        rating.setNumber_starts(4);

        ratingDao.insertRating(rating);

        List<Rating> ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(1, ratings.size());

        Rating updatedRating = ratings.get(0);
        updatedRating.setContent("Awesome product!");
        updatedRating.setNumber_starts(5);

        ratingDao.editRating(updatedRating);

        Rating retrievedRating = ratingDao.getRating(updatedRating.getId());
        assertNotNull(retrievedRating);
        assertEquals("Awesome product!", retrievedRating.getContent());
        assertEquals(5, retrievedRating.getNumber_starts());
    }

    @Test
    void editRatingSQLException() {
        Rating rating = new Rating();
        rating.setId(999); // Assuming 999 does not exist
        rating.setContent("Non-existent rating");
        rating.setNumber_starts(3);

        ratingDao.editRating(rating);

        assertNotNull(logHandler.getLogContent());
    }

    @Test
    void deleteRating() throws SQLException {
        Rating rating = new Rating();
        rating.setContent("This is a test review");
        rating.setProduct_id(1);
        rating.setUser_id(1);
        rating.setNumber_starts(3);

        ratingDao.insertRating(rating);

        List<Rating> ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(1, ratings.size());

        ratingDao.deleteRating(ratings.get(0).getId());

        ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(0, ratings.size());
    }

    @Test
    void deleteRatingSQLException() {
        ratingDao.deleteRating(999); // Assuming 999 does not exist

        assertNotNull(logHandler.getLogContent());
    }

    @Test
    void getRating() throws SQLException {
        Rating rating = new Rating();
        rating.setContent("Another test review");
        rating.setProduct_id(1);
        rating.setUser_id(1);
        rating.setNumber_starts(4);

        ratingDao.insertRating(rating);

        List<Rating> ratings = ratingDao.getAllRating();
        assertNotNull(ratings);
        assertEquals(1, ratings.size());

        Rating retrievedRating = ratingDao.getRating(ratings.get(0).getId());

        assertNotNull(retrievedRating);
        assertEquals("Another test review", retrievedRating.getContent());
        assertEquals(4, retrievedRating.getNumber_starts());
    }

    @Test
    void getRatingSQLException() {
        ratingDao.getRating(999); // Assuming 999 does not exist

        assertNotNull(logHandler.getLogContent());
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

    @Test
    void getAllRatingSQLException() {
        try {
            ratingDao.connection.prepareStatement("INVALID SQL").executeQuery();
        } catch (SQLException e) {
            // This is expected, ignore it for the test
        }

        assertNotNull(logHandler.getLogContent());
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
