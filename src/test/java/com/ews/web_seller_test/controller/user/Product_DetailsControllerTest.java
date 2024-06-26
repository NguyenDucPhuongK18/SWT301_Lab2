package com.ews.web_seller_test.controller.user;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class Product_DetailsControllerTest {

    @InjectMocks
    private Product_DetailsController productDetailsController;

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

    @Mock
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDoGet() throws Exception {
        // Mock data
        String mockProductId = "1";
        Product mockProduct = new Product(); // Create a mock Product object as needed
        when(productService.getProduct(Integer.parseInt(mockProductId))).thenReturn(mockProduct);

        List<Category> mockCategoryList = new ArrayList<>();
        mockCategoryList.add(new Category()); // Add some mock data as needed
        when(categoryService.getAllCategory()).thenReturn(mockCategoryList);

        List<Product> mockProductList = new ArrayList<>();
        mockProductList.add(new Product()); // Add some mock data as needed
        when(productService.getAllProduct()).thenReturn(mockProductList);

        when(request.getParameter("id")).thenReturn(mockProductId);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(new User()); // Mock user session data

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        // Call the doGet method
        productDetailsController.doGet(request, response);

        // Verify interactions
        verify(productService, times(1)).getProduct(Integer.parseInt(mockProductId));
        verify(categoryService, times(1)).getAllCategory();
        verify(productService, times(1)).getAllProduct();
        verify(request, times(1)).setAttribute(eq("categories"), anyList());
        verify(request, times(1)).setAttribute(eq("productList"), anyList());
        verify(request, times(1)).setAttribute(eq("product"), eq(mockProduct));
        verify(request, times(1)).setAttribute(eq("username"), anyString()); // Adjust as per session data
        verify(request, times(1)).setAttribute(eq("user"), any(User.class)); // Adjust as per session data
        verify(request, times(1)).setAttribute(eq("amount"), anyInt()); // Adjust as per session data
        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
