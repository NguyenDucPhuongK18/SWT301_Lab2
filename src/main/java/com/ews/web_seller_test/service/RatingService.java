package com.ews.web_seller_test.service;

import com.ews.web_seller_test.model.Rating;

import java.util.List;

public interface RatingService {

    void insertRating(Rating Rating);

    void editRating(Rating newRating);

    void deleteRating(int id);

    Rating getRating(int id);

    List<Rating> getAllRating();
}




