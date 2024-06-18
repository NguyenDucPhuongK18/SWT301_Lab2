package com.ews.web_seller_test.service.impl;

import com.ews.web_seller_test.dao.RatingDao;
import com.ews.web_seller_test.dao.impl.RatingDaoImpl;
import com.ews.web_seller_test.model.Rating;
import com.ews.web_seller_test.service.RatingService;

import java.util.List;

public class RatingServiceImpl implements RatingService {
    RatingDao ratingDao = new RatingDaoImpl();

    @Override
    public void insertRating(Rating rating) {
        ratingDao.insertRating(rating);
    }

    @Override
    public void editRating(Rating newRate) {
        ratingDao.editRating(newRate);
    }

    @Override
    public void deleteRating(int id) {
        ratingDao.deleteRating(id);
    }

    @Override
    public Rating getRating(int id) {
        return ratingDao.getRating(id);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingDao.getAllRating();
    }
}
