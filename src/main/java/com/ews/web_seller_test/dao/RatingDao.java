package com.ews.web_seller_test.dao;

import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.Rating;
import com.ews.web_seller_test.model.Role;

import java.util.List;

public interface RatingDao {
    void insertRating(Rating rating);

    void editRating(Rating newRating);

    void deleteRating(int id);

    Rating getRating(int id);

    List<Rating> getAllRating();
}
