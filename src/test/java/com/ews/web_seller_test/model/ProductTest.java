package com.ews.web_seller_test.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
//Done - TrongVV

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        // Initialize a sample Product object for testing
        Category category = new Category();
        category.setId(1);
        category.setName("TestCategory");

        product = new Product();
        product.setId(1);
        product.setCategory(category);
        product.setName("TestProduct");
        product.setPrice(100.0f);
        product.setDiscount(10.0f);
        product.setImage("test.jpg");
        product.setDescription("Test description");
        product.setCreated_at(new Date());
        product.setUpdated_at(new Date());
        product.setTotal_rating(5);
        product.setTotal_starts(100);
        product.setStatus(1);
    }

    @Test
    void getId() {
        assertEquals(1, product.getId());
    }

    @Test
    void setId() {
        product.setId(2);
        assertEquals(2, product.getId());
    }

    @Test
    void getCategory() {
        assertEquals("TestCategory", product.getCategory().getName());
    }

    @Test
    void setCategory() {
        Category newCategory = new Category();
        newCategory.setId(2);
        newCategory.setName("NewCategory");

        product.setCategory(newCategory);
        assertEquals("NewCategory", product.getCategory().getName());
    }

    @Test
    void getName() {
        assertEquals("TestProduct", product.getName());
    }

    @Test
    void setName() {
        product.setName("UpdatedProduct");
        assertEquals("UpdatedProduct", product.getName());
    }

    @Test
    void getPrice() {
        assertEquals(100.0f, product.getPrice());
    }

    @Test
    void setPrice() {
        product.setPrice(120.0f);
        assertEquals(120.0f, product.getPrice());
    }

    @Test
    void getDiscount() {
        assertEquals(10.0f, product.getDiscount());
    }

    @Test
    void setDiscount() {
        product.setDiscount(15.0f);
        assertEquals(15.0f, product.getDiscount());
    }

    @Test
    void getImage() {
        assertEquals("test.jpg", product.getImage());
    }

    @Test
    void setImage() {
        product.setImage("new_image.jpg");
        assertEquals("new_image.jpg", product.getImage());
    }

    @Test
    void getDescription() {
        assertEquals("Test description", product.getDescription());
    }

    @Test
    void setDescription() {
        product.setDescription("Updated description");
        assertEquals("Updated description", product.getDescription());
    }

    @Test
    void getCreated_at() {
        assertNotNull(product.getCreated_at());
    }

    @Test
    void setCreated_at() {
        Date newDate = new Date();
        product.setCreated_at(newDate);
        assertEquals(newDate, product.getCreated_at());
    }

    @Test
    void getUpdated_at() {
        assertNotNull(product.getUpdated_at());
    }

    @Test
    void setUpdated_at() {
        Date newDate = new Date();
        product.setUpdated_at(newDate);
        assertEquals(newDate, product.getUpdated_at());
    }

    @Test
    void getTotal_rating() {
        assertEquals(5, product.getTotal_rating());
    }

    @Test
    void setTotal_rating() {
        product.setTotal_rating(4);
        assertEquals(4, product.getTotal_rating());
    }

    @Test
    void getTotal_starts() {
        assertEquals(100, product.getTotal_starts());
    }

    @Test
    void setTotal_starts() {
        product.setTotal_starts(200);
        assertEquals(200, product.getTotal_starts());
    }

    @Test
    void getStatus() {
        assertEquals(1, product.getStatus());
    }

    @Test
    void setStatus() {
        product.setStatus(0);
        assertEquals(0, product.getStatus());
    }

    @Test
    void testToString() {
        String expectedString = "Product{id=1, category=Category{id=1, name='TestCategory'}, name='TestProduct', " +
                "price=100.0, discount=10.0, image='test.jpg', description='Test description', " +
                "created_at=" + product.getCreated_at() + ", updated_at=" + product.getUpdated_at() + ", " +
                "total_rating=5, total_starts=100}";
        assertEquals(expectedString, product.toString());
    }
}
