package com.ews.web_seller_test.service.impl;

import com.ews.web_seller_test.dao.ProductDao;
import com.ews.web_seller_test.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
//Done - TrongVV
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductDao productDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void insertProduct() {
        Product product = new Product();
        doNothing().when(productDao).insertProduct(product);

        productService.insertProduct(product);

        verify(productDao, times(1)).insertProduct(product);
    }

    @Test
    void getIdInsertProduct() {
        Product product = new Product();
        int expectedId = 1;
        when(productDao.getIdInsertProduct(product)).thenReturn(expectedId);

        int id = productService.getIdInsertProduct(product);

        assertEquals(expectedId, id);
        verify(productDao, times(1)).getIdInsertProduct(product);
    }

    @Test
    void searchProductByName() {
        String productName = "Test";
        int index = 1;
        int size = 10;
        List<Product> expectedProducts = new ArrayList<>();
        when(productDao.searchProductByName(productName, index, size)).thenReturn(expectedProducts);

        List<Product> products = productService.searchProductByName(productName, index, size);

        assertEquals(expectedProducts, products);
        verify(productDao, times(1)).searchProductByName(productName, index, size);
    }

    @Test
    void editProduct() {
        Product newProduct = new Product();
        newProduct.setId(1);
        Product oldProduct = new Product();
        oldProduct.setId(1);

        when(productDao.getProduct(newProduct.getId())).thenReturn(oldProduct);
        doNothing().when(productDao).editProduct(any(Product.class));

        productService.editProduct(newProduct);

        verify(productDao, times(1)).getProduct(newProduct.getId());
        verify(productDao, times(1)).editProduct(oldProduct);
    }

    @Test
    void countProduct() {
        String txtSearch = "Test";
        int expectedCount = 10;
        when(productDao.countProduct(txtSearch)).thenReturn(expectedCount);

        int count = productService.countProduct(txtSearch);

        assertEquals(expectedCount, count);
        verify(productDao, times(1)).countProduct(txtSearch);
    }

    @Test
    void countProductCategory() {
        String textCategory = "Test";
        int expectedCount = 5;
        when(productDao.countProductCategory(textCategory)).thenReturn(expectedCount);

        int count = productService.countProductCategory(textCategory);

        assertEquals(expectedCount, count);
        verify(productDao, times(1)).countProductCategory(textCategory);
    }

    @Test
    void searchProductByCategory() {
        String categoryName = "TestCategory";
        int index = 1;
        int size = 10;
        List<Product> expectedProducts = new ArrayList<>();
        when(productDao.searchProductByCategory(categoryName, index, size)).thenReturn(expectedProducts);

        List<Product> products = productService.searchProductByCategory(categoryName, index, size);

        assertEquals(expectedProducts, products);
        verify(productDao, times(1)).searchProductByCategory(categoryName, index, size);
    }

    @Test
    void deleteProduct() {
        int productId = 1;
        doNothing().when(productDao).deleteProduct(productId);

        productService.deleteProduct(productId);

        verify(productDao, times(1)).deleteProduct(productId);
    }

    @Test
    void getProduct() {
        int productId = 1;
        Product expectedProduct = new Product();
        when(productDao.getProduct(productId)).thenReturn(expectedProduct);

        Product product = productService.getProduct(productId);

        assertEquals(expectedProduct, product);
        verify(productDao, times(1)).getProduct(productId);
    }

    @Test
    void getAllProduct() {
        List<Product> expectedProducts = new ArrayList<>();
        when(productDao.getAllProduct()).thenReturn(expectedProducts);

        List<Product> products = productService.getAllProduct();

        assertEquals(expectedProducts, products);
        verify(productDao, times(1)).getAllProduct();
    }

    @Test
    void searchProduct() {
        String productName = "Test";
        List<Product> expectedProducts = new ArrayList<>();
        when(productDao.searchProduct(productName)).thenReturn(expectedProducts);

        List<Product> products = productService.searchProduct(productName);

        assertEquals(expectedProducts, products);
        verify(productDao, times(1)).searchProduct(productName);
    }

    @Test
    void searchProductByCategory_int() {
        int categoryId = 1;
        List<Product> expectedProducts = new ArrayList<>();
        when(productDao.searchProductByCategory(categoryId)).thenReturn(expectedProducts);

        List<Product> products = productService.searchProductByCategory(categoryId);

        assertEquals(expectedProducts, products);
        verify(productDao, times(1)).searchProductByCategory(categoryId);
    }

    @Test
    void searchProductByName_string() {
        String productName = "Test";
        List<Product> expectedProducts = new ArrayList<>();
        when(productDao.searchProductByName(productName)).thenReturn(expectedProducts);

        List<Product> products = productService.searchProductByName(productName);

        assertEquals(expectedProducts, products);
        verify(productDao, times(1)).searchProductByName(productName);
    }
}
