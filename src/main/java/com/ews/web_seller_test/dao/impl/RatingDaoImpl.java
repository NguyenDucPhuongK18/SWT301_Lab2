package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.dao.MyDAO;
import com.ews.web_seller_test.dao.RatingDao;
import com.ews.web_seller_test.model.Rating;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RatingDaoImpl extends MyDAO implements RatingDao {
    private static final Logger LOGGER = Logger.getLogger(RatingDaoImpl.class.getName());
    @Override
    public void insertRating(Rating rating) {
        xSql = "INSERT INTO Rating (content, product_id, user_id, number_starts) VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, rating.getContent());
            ps.setInt(2, rating.getProduct_id());
            ps.setInt(3, rating.getUser_id());
            ps.setInt(4, rating.getNumber_starts());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while inserting rating", e);
        }
    }

    @Override
    public void editRating(Rating newRating) {
        xSql = "UPDATE Rating SET content=?, number_starts=?, updated_id= CURRENT_TIMESTAMP() WHERE id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, newRating.getContent());
            ps.setInt(2, newRating.getNumber_starts());
            ps.setInt(3, newRating.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while editing rating", e);
        }
    }

    @Override
    public void deleteRating(int id) {
        xSql = "DELETE FROM Rating WHERE id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while deleting rating", e);
        }
    }

    @Override
    public Rating getRating(int id) {
        Rating rating = null;
        xSql = "SELECT * FROM Rating WHERE id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                rating = new Rating();
                rating.setId(rs.getInt("id"));
                rating.setContent(rs.getString("content"));
                rating.setProduct_id(rs.getInt("product_id"));
                rating.setUser_id(rs.getInt("user_id"));
                rating.setNumber_starts(rs.getInt("number_starts"));
                rating.setCreated_id(rs.getTimestamp("created_id"));
                rating.setUpdated_id(rs.getTimestamp("updated_id"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while getting rating", e);
        }
        return rating;
    }

    @Override
    public List<Rating> getAllRating() {
        List<Rating> ratings = new ArrayList<>();
        String sql = "SELECT * FROM Rating";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rating rating = new Rating();
                rating.setId(rs.getInt("id"));
                rating.setContent(rs.getString("content"));
                rating.setProduct_id(rs.getInt("product_id"));
                rating.setUser_id(rs.getInt("user_id"));
                rating.setNumber_starts(rs.getInt("number_starts"));
                rating.setCreated_id(rs.getTimestamp("created_id"));
                rating.setUpdated_id(rs.getTimestamp("updated_id"));
                ratings.add(rating);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while getting all ratings", e);
        }
        return ratings;
    }
}
