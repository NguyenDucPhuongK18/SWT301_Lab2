package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductDaoImplTest {

    @InjectMocks
    private ProductDaoImpl productDaoImpl;

    @Mock
    private CategoryService categoryService;

    @Mock
    private Connection con;

    @Mock
    private PreparedStatement ps;

    @Mock
    private ResultSet rs;

    @Captor
    private ArgumentCaptor<Integer> intCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productDaoImpl = new ProductDaoImpl();
        productDaoImpl.con = con;
    }

    @Test
    void insertProduct() throws SQLException {
        Product product = new Product();

        product.setCategory(new Category());

        product.getCategory().setId(1);

        product.setName("Test Product");

        product.setPrice(100.0f);

        product.setDiscount(10.0f);

        product.setImage("image.jpg");

        product.setDescription("Description");

        product.setTotal_rating(5);

        product.setTotal_starts(4);
        
        product.setStatus(1);

        when(con.prepareStatement(any(String.class))).thenReturn(ps);

        productDaoImpl.insertProduct(product);

        verify(ps, times(1)).executeUpdate();
        verify(ps, times(1)).close();
    }

    @Test
    void getIdInsertProduct() throws SQLException {
        Product product = new Product();
        product.setCategory(new Category());
        product.getCategory().setId(1);
        product.setName("Test Product");
        product.setPrice(100.0f);
        product.setDiscount(10.0f);
        product.setDescription("Description");

        when(con.prepareStatement(any(String.class), anyInt())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);
        when(ps.getGeneratedKeys()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(1);

        int productId = productDaoImpl.getIdInsertProduct(product);

        assertEquals(1, productId);
        verify(ps, times(1)).close();
    }

    @Test
    void editProduct() throws SQLException {
        Product product = new Product();
        product.setId(1);
        product.setCategory(new Category());
        product.getCategory().setId(1);
        product.setName("Test Product");
        product.setPrice(100.0f);
        product.setDiscount(10.0f);
        product.setImage("image.jpg");
        product.setDescription("Description");
        product.setTotal_rating(5);
        product.setTotal_starts(4);
        product.setStatus(1);

        when(con.prepareStatement(any(String.class))).thenReturn(ps);

        productDaoImpl.editProduct(product);

        verify(ps, times(1)).executeUpdate();
        verify(ps, times(1)).close();
    }

    @Test
    void deleteProduct() throws SQLException {
        int productId = 1;

        when(con.prepareStatement(any(String.class))).thenReturn(ps);

        productDaoImpl.deleteProduct(productId);

        verify(ps, times(1)).setInt(1, productId);
        verify(ps, times(1)).executeUpdate();
        verify(ps, times(1)).close();
    }

    @Test
    void getProduct() throws SQLException {
        int productId = 1;

        when(con.prepareStatement(any(String.class))).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt("id")).thenReturn(productId);
        when(rs.getInt("category_id")).thenReturn(1);
        when(rs.getString("p_name")).thenReturn("Test Product");
        when(rs.getFloat("price")).thenReturn(100.0f);
        when(rs.getFloat("discount")).thenReturn(10.0f);
        when(rs.getString("image")).thenReturn("image.jpg");
        when(rs.getString("description")).thenReturn("Description");
        when(rs.getInt("total_rating")).thenReturn(5);
        when(rs.getInt("total_stars")).thenReturn(4);
        when(rs.getInt("status")).thenReturn(1);

        Product product = productDaoImpl.getProduct(productId);

        assertNotNull(product);
        assertEquals(productId, product.getId());
        assertEquals("Test Product", product.getName());
        verify(ps, times(1)).close();
    }

    @Test
    void getAllProduct() throws SQLException {

    }

    @Test
    void searchProduct() throws SQLException {

    }

    @Test
    void searchProductByCategory() throws SQLException {

    }
}
