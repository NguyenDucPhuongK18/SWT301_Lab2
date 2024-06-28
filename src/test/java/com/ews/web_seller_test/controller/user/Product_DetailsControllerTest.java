package com.ews.web_seller_test.controller.user;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
//Done - TrongVV
class Product_DetailsControllerTest {

    @InjectMocks
    private Product_DetailsController controller;

    @Mock
    private ProductService productService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock behavior for request and session
        when(request.getParameter("id")).thenReturn("1");
        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testDoGet() throws Exception {
        // Mock data
        Product mockProduct = new Product(1, "Product1");
        mockProduct.setId(1);
        mockProduct.setName("Test Product");
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(new Category(1, "TestCategory"));
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(mockProduct);

        // Mock service responses
        when(productService.getProduct(anyInt())).thenReturn(mockProduct);
        when(categoryService.getAllCategory()).thenReturn(mockCategories);
        when(productService.getAllProduct()).thenReturn(mockProducts);

        // Mock getRequestDispatcher to return null
        when(request.getRequestDispatcher("/views/user/single-product.jsp")).thenReturn(null);

        // Invoke controller method
        assertThrows(ServletException.class, () -> controller.doGet(request, response));

        // Verify request attributes are set correctly
        verify(request).setAttribute("product", mockProduct);
        verify(request).setAttribute("categories", mockCategories);
        verify(request).setAttribute("productList", mockProducts);

        // Verify session attribute retrieval
        verify(session).getAttribute("account");
    }
}
