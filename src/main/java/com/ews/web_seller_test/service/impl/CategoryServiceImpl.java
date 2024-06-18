package com.ews.web_seller_test.service.impl;

import com.ews.web_seller_test.dao.CategoryDao;
import com.ews.web_seller_test.dao.impl.CategoryDaoImpl;
import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public void insertCategory(Category category) {
        categoryDao.insertCategory(category);
    }

    @Override
    public void editCategory(Category newCategory) {
        Category oldCate = categoryDao.getCategory(newCategory.getId());
        oldCate.setName(newCategory.getName());
        categoryDao.editCategory(oldCate);
    }

    @Override
    public void deleteCategory(int id) {
        categoryDao.deleteCategory(id);
    }

    @Override
    public Category getCategory(int id) {
        return categoryDao.getCategory(id);
    }

    @Override
    public Category getCategory(String name) {
        return categoryDao.getCategory(name);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }

    @Override
    public List<Category> searchCategory(String username) {
        return categoryDao.searchCategory(username);
    }
}
