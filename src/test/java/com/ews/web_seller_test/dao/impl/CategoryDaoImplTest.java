package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.model.Category;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDaoImplTest {
    CategoryDaoImpl categoryDao = new CategoryDaoImpl();

    @Test
    void insertCategory() throws SQLException {
        Date checkedDate = new Date();

        categoryDao.connection.prepareStatement("TRUNCATE TABLE Category").executeUpdate();

        categoryDao.insertCategory(new Category(1, "Sport", checkedDate, checkedDate));
        List<Category> categories = categoryDao.getAllCategory();

        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals(1, categories.get(0).getId());
        assertEquals("Sport", categories.get(0).getName());
//        assertEquals(checkedDate, categories.get(0).getCreated_at());
//        assertEquals(checkedDate, categories.get(0).getUpdated_at());
    }

    @Test
    void editCategory() throws SQLException {
        Date checkedDate = new Date();

        categoryDao.connection.prepareStatement("TRUNCATE TABLE Category").executeUpdate();

        categoryDao.insertCategory(new Category(1, "Sport", checkedDate, checkedDate));
        List<Category> categories = categoryDao.getAllCategory();

        Category updated = categories.get(0);
        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals(1, updated.getId());
        assertEquals("Sport", updated.getName());

        updated.setName("Supersport");

        categoryDao.editCategory(updated);

        updated = categoryDao.getCategory(updated.getId());
        assertEquals("Supersport", updated.getName());
    }

    @Test
    void deleteCategory() throws SQLException {
        Date checkedDate = new Date();
        categoryDao.connection.prepareStatement("TRUNCATE TABLE Category").executeUpdate();
        categoryDao.insertCategory(new Category(1, "Sport", checkedDate, checkedDate));

        List<Category> categories = categoryDao.getAllCategory();

        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals(1, categories.get(0).getId());
        assertEquals("Sport", categories.get(0).getName());

        categoryDao.deleteCategory(1);
        categories = categoryDao.getAllCategory();
        assertNotNull(categories);
        assertEquals(0, categories.size());
    }

    @Test
    void getCategory() {

    }

    @Test
    void searchCategory() throws SQLException {
        Date checkedDate = new Date();
        categoryDao.connection.prepareStatement("TRUNCATE TABLE Category").executeUpdate();

        categoryDao.insertCategory(new Category(1, "Sport", checkedDate, checkedDate));
        categoryDao.insertCategory(new Category(2, "Electronics", checkedDate, checkedDate));
        categoryDao.insertCategory(new Category(3, "Education", checkedDate, checkedDate));
        categoryDao.insertCategory(new Category(4, "Vehicles", checkedDate, checkedDate));
        categoryDao.insertCategory(new Category(5, "Cooking", checkedDate, checkedDate));
        categoryDao.insertCategory(new Category(6, "Home", checkedDate, checkedDate));

        List<Category> categories = categoryDao.getAllCategory();
        assertNotNull(categories);
        assertEquals(6, categories.size());

        List<Category> searchedCategory = categoryDao.searchCategory("S");
        assertNotNull(searchedCategory);
        assertEquals(3, searchedCategory.size());
    }
}