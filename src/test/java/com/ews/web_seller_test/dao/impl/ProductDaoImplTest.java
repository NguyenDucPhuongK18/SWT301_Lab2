package com.ews.web_seller_test.dao.impl;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
//Doing - TrongVV

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
//        int productId = 1;
//        Category category = new Category();
//        category.setId(1);
//        category.setName("Category");
//
//        String xSql = "SELECT Product.id, Product.category_id, Product.name AS p_name, Product.price, Product.discount, Product.image, Product.description, Product.total_starts, Product.total_rating, Product.status, Category.name AS c_name, Category.id AS c_id "
//                + "FROM Product INNER JOIN Category ON Product.category_id = Category.id WHERE Product.id= ? ";
//
//        when(con.prepareStatement(xSql)).thenReturn(ps);
//        when(ps.executeQuery()).thenReturn(rs);
//        when(rs.next()).thenReturn(true);
//        when(rs.getInt("id")).thenReturn(1);
//        when(rs.getString("p_name")).thenReturn("Test Product");
//        when(rs.getFloat("price")).thenReturn(100.0f);
//        when(rs.getFloat("discount")).thenReturn(10.0f);
//        when(rs.getString("image")).thenReturn("image.jpg");
//        when(rs.getString("description")).thenReturn("Description");
//        when(rs.getInt("total_rating")).thenReturn(5);
//        when(rs.getInt("total_starts")).thenReturn(4);
//        when(rs.getInt("status")).thenReturn(1);
//        when(rs.getInt("c_id")).thenReturn(1);
//        when(categoryService.getCategory(anyInt())).thenReturn(category);
//
//        Product product = productDaoImpl.getProduct(productId);
//
//        assertNotNull(product);
//        assertEquals(1, product.getId());
//        assertEquals("Test Product", product.getName());
//        assertEquals(100.0f, product.getPrice());
//        assertEquals(10.0f, product.getDiscount());
//        assertEquals("image.jpg", product.getImage());
//        assertEquals("Description", product.getDescription());
//        assertEquals(5, product.getTotal_rating());
//        assertEquals(4, product.getTotal_starts());
//        assertEquals(1, product.getStatus());
//        assertEquals(category, product.getCategory());
//
//        verify(ps, times(1)).setInt(1, productId);
//        verify(ps, times(1)).executeQuery();
//        verify(rs, times(1)).next();
//        verify(ps, times(1)).close();
//        verify(rs, times(1)).close();
    }

    @Test
    void getAllProduct() throws SQLException {
//        Category category = new Category();
//        category.setId(1);
//        category.setName("Category");
//
//        when(con.prepareStatement(any(String.class))).thenReturn(ps);
//        when(ps.executeQuery()).thenReturn(rs);
//        when(rs.next()).thenReturn(true).thenReturn(false);
//        when(rs.getInt("id")).thenReturn(1);
//        when(rs.getString("p_name")).thenReturn("Test Product");
//        when(rs.getFloat("price")).thenReturn(100.0f);
//        when(rs.getFloat("discount")).thenReturn(10.0f);
//        when(rs.getString("image")).thenReturn("image.jpg");
//        when(rs.getString("description")).thenReturn("Description");
//        when(rs.getInt("total_rating")).thenReturn(5);
//        when(rs.getInt("total_starts")).thenReturn(4);
//        when(rs.getInt("status")).thenReturn(1);
//        when(rs.getInt("c_id")).thenReturn(1);
//        when(categoryService.getCategory(anyInt())).thenReturn(category);
//
//        List<Product> products = productDaoImpl.getAllProduct();
//
//        assertNotNull(products);
//        assertFalse(products.isEmpty());
//        assertEquals("Test Product", products.get(0).getName());
//        verify(ps, times(1)).close();
    }

    @Test
    void searchProduct() throws SQLException {
//        String productName = "Test";
//        Category category = new Category();
//        category.setId(1);
//        category.setName("Category");
//
//        when(con.prepareStatement(any(String.class))).thenReturn(ps);
//        when(ps.executeQuery()).thenReturn(rs);
//        when(rs.next()).thenReturn(true).thenReturn(false);
//        when(rs.getInt("id")).thenReturn(1);
//        when(rs.getString("p_name")).thenReturn("Test Product");
//        when(rs.getFloat("price")).thenReturn(100.0f);
//        when(rs.getFloat("discount")).thenReturn(10.0f);
//        when(rs.getString("image")).thenReturn("image.jpg");
//        when(rs.getString("description")).thenReturn("Description");
//        when(rs.getInt("total_rating")).thenReturn(5);
//        when(rs.getInt("total_starts")).thenReturn(4);
//        when(rs.getInt("status")).thenReturn(1);
//        when(rs.getInt("category_id")).thenReturn(1);
//        when(categoryService.getCategory(anyInt())).thenReturn(category);
//
//        List<Product> products = productDaoImpl.searchProduct(productName);
//
//        assertNotNull(products);
//        assertFalse(products.isEmpty());
//        assertEquals("Test Product", products.get(0).getName());
//        verify(ps, times(1)).close();
    }

    @Test
    void searchProductByCategory() throws SQLException {
//        int categoryId = 1;
//        Category category = new Category();
//        category.setId(1);
//        category.setName("Category");
//
//        when(con.prepareStatement(any(String.class))).thenReturn(ps);
//        when(ps.executeQuery()).thenReturn(rs);
//        when(rs.next()).thenReturn(true).thenReturn(false);
//        when(rs.getInt("id")).thenReturn(1);
//        when(rs.getString("p_name")).thenReturn("Test Product");
//        when(rs.getFloat("price")).thenReturn(100.0f);
//        when(rs.getFloat("discount")).thenReturn(10.0f);
//        when(rs.getString("image")).thenReturn("image.jpg");
//        when(rs.getString("description")).thenReturn("Description");
//        when(rs.getInt("total_rating")).thenReturn(5);
//        when(rs.getInt("total_starts")).thenReturn(4);
//        when(rs.getInt("status")).thenReturn(1);
//        when(rs.getInt("c_id")).thenReturn(1);
//        when(categoryService.getCategory(anyInt())).thenReturn(category);
//
//        List<Product> products = productDaoImpl.searchProductByCategory(categoryId);
//
//        assertNotNull(products);
//        assertFalse(products.isEmpty());
//        assertEquals("Test Product", products.get(0).getName());
//        verify(ps, times(1)).close();
    }

    @Test
    void searchProductByName() throws SQLException {
//        String productName = "Test Product";
//        Category category = new Category();
//        category.setId(1);
//        category.setName("Category");
//
//        when(con.prepareStatement(any(String.class))).thenReturn(ps);
//        when(ps.executeQuery()).thenReturn(rs);
//        when(rs.next()).thenReturn(true).thenReturn(false);
//        when(rs.getInt("id")).thenReturn(1);
//        when(rs.getString("p_name")).thenReturn("Test Product");
//        when(rs.getFloat("price")).thenReturn(100.0f);
//        when(rs.getFloat("discount")).thenReturn(10.0f);
//        when(rs.getString("image")).thenReturn("image.jpg");
//        when(rs.getString("description")).thenReturn("Description");
//        when(rs.getInt("total_rating")).thenReturn(5);
//        when(rs.getInt("total_starts")).thenReturn(4);
//        when(rs.getInt("status")).thenReturn(1);
//        when(rs.getInt("category_id")).thenReturn(1);
//        when(categoryService.getCategory(anyInt())).thenReturn(category);
//
//        List<Product> products = productDaoImpl.searchProductByName(productName);
//
//        assertNotNull(products);
//        assertFalse(products.isEmpty());
//        assertEquals("Test Product", products.get(0).getName());
//        verify(ps, times(1)).close();
    }

    @Test
    void countProduct() throws SQLException {
//        String txtSearch = "test";
//        String xSql = "SELECT COUNT(*) as count FROM Product WHERE name LIKE ?";
//        int expectedCount = 10; // Set your expected count here
//
//        when(con.prepareStatement(any(String.class))).thenReturn(ps);
//        when(ps.executeQuery()).thenReturn(rs);
//        when(rs.next()).thenReturn(true);
//        when(rs.getInt("count")).thenReturn(expectedCount);
//
//        int count = productDaoImpl.countProduct(txtSearch);
//
//        assertEquals(expectedCount, count);
//        verify(ps, times(1)).setString(1, "%" + txtSearch + "%");
//        verify(ps, times(1)).executeQuery();
//        verify(ps, times(1)).close();
//        verify(rs, times(1)).close();
    }

    @Test
    void countProductCategory() throws SQLException {
//        String textCategory = "test";
//        String xSql = "SELECT COUNT(*) as count FROM Product INNER JOIN Category C on C.id = Product.category_id WHERE C.name LIKE ?";
//        int expectedCount = 5; // Set your expected count here
//
//        when(con.prepareStatement(any(String.class))).thenReturn(ps);
//        when(ps.executeQuery()).thenReturn(rs);
//        when(rs.next()).thenReturn(true);
//        when(rs.getInt("count")).thenReturn(expectedCount);
//
//        int count = productDaoImpl.countProductCategory(textCategory);
//
//        assertEquals(expectedCount, count);
//        verify(ps, times(1)).setString(1, "%" + textCategory + "%");
//        verify(ps, times(1)).executeQuery();
//        verify(ps, times(1)).close();
//        verify(rs, times(1)).close();
    }

    @Test
    void testSearchProductByCategory() throws SQLException {
//        int categoryId = 1;
//        Category category = new Category();
//        category.setId(1);
//        category.setName("Category");
//
//        when(con.prepareStatement(any(String.class))).thenReturn(ps);
//        when(ps.executeQuery()).thenReturn(rs);
//        when(rs.next()).thenReturn(true).thenReturn(false);
//        when(rs.getInt("id")).thenReturn(1);
//        when(rs.getString("p_name")).thenReturn("Test Product");
//        when(rs.getFloat("price")).thenReturn(100.0f);
//        when(rs.getFloat("discount")).thenReturn(10.0f);
//        when(rs.getString("image")).thenReturn("image.jpg");
//        when(rs.getString("description")).thenReturn("Description");
//        when(rs.getInt("total_rating")).thenReturn(5);
//        when(rs.getInt("total_starts")).thenReturn(4);
//        when(rs.getInt("status")).thenReturn(1);
//        when(rs.getInt("c_id")).thenReturn(1);
//        when(categoryService.getCategory(anyInt())).thenReturn(category);
//
//        List<Product> products = productDaoImpl.searchProductByCategory(categoryId);
//
//        assertNotNull(products);
//        assertFalse(products.isEmpty());
//        assertEquals("Test Product", products.get(0).getName());
//        verify(ps, times(1)).close();
    }

    @Test
    void testSearchProductByName() throws SQLException {
//        String productName = "Test Product";
//        Category category = new Category();
//        category.setId(1);
//        category.setName("Category");
//
//        when(con.prepareStatement(any(String.class))).thenReturn(ps);
//        when(ps.executeQuery()).thenReturn(rs);
//        when(rs.next()).thenReturn(true).thenReturn(false);
//        when(rs.getInt("id")).thenReturn(1);
//        when(rs.getString("p_name")).thenReturn("Test Product");
//        when(rs.getFloat("price")).thenReturn(100.0f);
//        when(rs.getFloat("discount")).thenReturn(10.0f);
//        when(rs.getString("image")).thenReturn("image.jpg");
//        when(rs.getString("description")).thenReturn("Description");
//        when(rs.getInt("total_rating")).thenReturn(5);
//        when(rs.getInt("total_starts")).thenReturn(4);
//        when(rs.getInt("status")).thenReturn(1);
//        when(rs.getInt("category_id")).thenReturn(1);
//        when(categoryService.getCategory(anyInt())).thenReturn(category);
//
//        List<Product> products = productDaoImpl.searchProductByName(productName);
//
//        assertNotNull(products);
//        assertFalse(products.isEmpty());
//        assertEquals("Test Product", products.get(0).getName());
//        verify(ps, times(1)).close();
    }
}
