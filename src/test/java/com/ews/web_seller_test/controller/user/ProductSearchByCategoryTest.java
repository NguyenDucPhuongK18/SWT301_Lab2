package com.ews.web_seller_test.controller.user;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
//Done - TrongVV

class ProductSearchByCategoryTest {

    @InjectMocks
    private ProductSearchByCategory servlet;

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
    private RequestDispatcher dispatcher;

    @Mock
    private ServletContext context;

    @BeforeEach
    void setUp() throws ServletException {
        MockitoAnnotations.openMocks(this);
        servlet.init();
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getServletContext()).thenReturn(context);
    }

    @Test
    void testDoGet() throws ServletException, IOException {
        // Mock data
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(new Category(1, "TestCategory"));

        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product(1, "Product1"));
        mockProducts.add(new Product(2, "Product2"));

        when(categoryService.getAllCategory()).thenReturn(mockCategories);
        when(productService.countProductCategory(anyString())).thenReturn(2);
        when(productService.searchProductByCategory(anyString(), anyInt(), anyInt())).thenReturn(mockProducts);

        // Simulate request parameters
        when(request.getParameter("txtSearch")).thenReturn("TestCategory");
        when(request.getParameter("indexPage")).thenReturn("1");

        // Mock session and user
        User mockUser = new User();
        mockUser.setUsername("testuser");
        mockUser.setFull_name("Test User");
        when(session.getAttribute("account")).thenReturn(mockUser);

        // Invoke servlet doGet method
        servlet.doGet(request, response);

        // Verify request attributes are set correctly
        verify(request).setAttribute("username", "testuser");
        verify(request).setAttribute("user", mockUser);
        verify(request).setAttribute("name", "Test User");
        verify(request).setAttribute("categoryList", mockCategories);
        verify(request).setAttribute("checkCategorySearch", true);
        verify(request).setAttribute("count", 2);
        verify(request).setAttribute("end", 1);
        verify(request).setAttribute("indexPage", 1);
        verify(request).setAttribute("productList", mockProducts);
        verify(request).setAttribute("txtSearch", "TestCategory");

        // Verify forward to correct JSP
        verify(request.getRequestDispatcher("/views/user/shopping.jsp")).forward(request, response);
    }

}
