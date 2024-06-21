package com.ews.web_seller_test.service;

import com.ews.web_seller_test.model.Category;

import java.util.List;

public interface CategoryService {
    boolean insertCategory(Category category);

    void editCategory(Category category);

    void deleteCategory(int id);

    Category getCategory(int id);

    Category getCategory(String name);

    List<Category> getAllCategory();

    List<Category> searchCategory(String username);
}
