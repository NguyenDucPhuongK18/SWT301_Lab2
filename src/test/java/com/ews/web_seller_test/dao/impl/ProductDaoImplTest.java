package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Done - TrongVV
class ProductDaoImplTest {
    ProductDaoImpl productDao = new ProductDaoImpl();

    @Test
    void insertProduct() throws SQLException {
        Category category = new Category(1, "Test Category");

        Product product = new Product(category, "Test Product", 100.0f, 10.0f, "test.jpg", "Test Description", 5, 5, 1);
        productDao.connection.prepareStatement("TRUNCATE TABLE Product").executeUpdate();

        productDao.insertProduct(product);

        List<Product> products = productDao.getAllProduct();

        assertEquals(1, products.size());
        Product insertedProduct = products.get(0);
        assertEquals("Test Product", insertedProduct.getName());
        assertEquals(100.0f, insertedProduct.getPrice());
        assertEquals(10.0f, insertedProduct.getDiscount());
        assertEquals("test.jpg", insertedProduct.getImage());
        assertEquals("Test Description", insertedProduct.getDescription());
        assertEquals(5, insertedProduct.getTotal_rating());
        assertEquals(5, insertedProduct.getTotal_starts());
        assertEquals(1, insertedProduct.getStatus());
        assertEquals(category.getId(), insertedProduct.getCategory().getId());
    }

    @Test
    void getIdInsertProduct() throws SQLException {
        Category category = new Category(1, "Test Category");
        Product product = new Product(category, "Test Product", 100.0f, 10.0f, "test.jpg", "Test Description", 5, 5, 1);
        productDao.connection.prepareStatement("TRUNCATE TABLE Product").executeUpdate();
        int generatedProductId = productDao.getIdInsertProduct(product);

        assertNotEquals(0, generatedProductId); // Ensure the generated ID is not zero
        Product retrievedProduct = productDao.getProduct(generatedProductId);

        assertNotNull(retrievedProduct); // Ensure the product with the generated ID exists
        assertEquals(product.getName(), retrievedProduct.getName());
        assertEquals(product.getPrice(), retrievedProduct.getPrice());
        assertEquals(product.getDiscount(), retrievedProduct.getDiscount());
        assertEquals(product.getImage(), retrievedProduct.getImage());
        assertEquals(product.getDescription(), retrievedProduct.getDescription());
        assertEquals(product.getTotal_rating(), retrievedProduct.getTotal_rating());
        assertEquals(product.getTotal_starts(), retrievedProduct.getTotal_starts());
        assertEquals(product.getStatus(), retrievedProduct.getStatus());
        assertEquals(product.getCategory().getId(), retrievedProduct.getCategory().getId());
    }


    @Test
    void editProduct() throws SQLException {
        Category category = new Category(1, "Test Category");
        Product product = new Product(category, "Test Product", 100.0f, 10.0f, "test.jpg", "Test Description", 5, 5, 1);
        productDao.connection.prepareStatement("TRUNCATE TABLE Product").executeUpdate();
        int generatedProductId = productDao.getIdInsertProduct(product);

        assertNotEquals(0, generatedProductId); // Ensure the generated ID is not zero
        product = productDao.getProduct(generatedProductId);

        product.setName("Updated Product");
        product.setPrice(150.0f);
        productDao.editProduct(product);

        Product updatedProduct = productDao.getProduct(product.getId());

        assertNotNull(updatedProduct);
        assertEquals("Updated Product", updatedProduct.getName());
        assertEquals(150.0f, updatedProduct.getPrice());
    }

    @Test
    void deleteProduct() throws SQLException {
        Category category = new Category(1, "Test Category");
        Product product = new Product(category, "Test Product", 100.0f, 10.0f, "test.jpg", "Test Description", 5, 5, 1);
        productDao.insertProduct(product);

        productDao.deleteProduct(product.getId());

        Product deletedProduct = productDao.getProduct(product.getId());

        assertNull(deletedProduct);
    }

    @Test
    void getProduct() throws SQLException {
        Category category = new Category(1, "Test Category");
        Product product = new Product(category, "Test Product", 100.0f, 10.0f, "test.jpg", "Test Description", 5, 5, 1);
        productDao.connection.prepareStatement("TRUNCATE TABLE Product").executeUpdate();
        int generatedProductId = productDao.getIdInsertProduct(product);

        assertNotEquals(0, generatedProductId); // Ensure the generated ID is not zero
        product = productDao.getProduct(generatedProductId);

        Product retrievedProduct = productDao.getProduct(product.getId());

        assertNotNull(retrievedProduct);
        assertEquals(product.getId(), retrievedProduct.getId());
    }

    @Test
    void getAllProduct() throws SQLException {
        Category category1 = new Category(1, "Category 1");
        Category category2 = new Category(2, "Category 2");

        Product product1 = new Product(category1, "Product 1", 100.0f, 10.0f, "image1.jpg", "Description 1", 5, 5, 1);
        Product product2 = new Product(category2, "Product 2", 200.0f, 20.0f, "image2.jpg", "Description 2", 4, 4, 1);

        productDao.insertProduct(product1);
        productDao.insertProduct(product2);

        List<Product> products = productDao.getAllProduct();

        assertEquals(2, products.size());
    }

    @Test
    void searchProduct() throws SQLException {
        Category category = new Category(1, "Test Category");
        Product product1 = new Product(category, "Product 1", 100.0f, 10.0f, "image1.jpg", "Description 1", 5, 5, 1);
        Product product2 = new Product(category, "Product 2", 200.0f, 20.0f, "image2.jpg", "Description 2", 4, 4, 1);
        productDao.connection.prepareStatement("TRUNCATE TABLE Product").executeUpdate();

        productDao.insertProduct(product1);
        productDao.insertProduct(product2);

        List<Product> foundProducts = productDao.searchProduct("Product");

        assertEquals(2, foundProducts.size());
    }
    @Test
    void searchProductByCategory() throws SQLException {
        Category category1 = new Category(1, "Category 1");
        Category category2 = new Category(2, "Category 2");

        Product product1 = new Product(category1, "Product 1", 100.0f, 10.0f, "image1.jpg", "Description 1", 5, 5, 1);
        Product product2 = new Product(category2, "Product 2", 200.0f, 20.0f, "image2.jpg", "Description 2", 4, 4, 1);

        productDao.insertProduct(product1);
        productDao.insertProduct(product2);

        List<Product> foundProducts = productDao.searchProductByCategory(category1.getId());

        assertEquals(1, foundProducts.size());
        assertEquals("Product 1", foundProducts.get(0).getName());
    }
}
