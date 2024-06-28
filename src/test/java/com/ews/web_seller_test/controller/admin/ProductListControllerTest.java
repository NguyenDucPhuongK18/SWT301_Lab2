package com.ews.web_seller_test.controller.admin;

import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.model.Role;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.Order_DetailsService;
import com.ews.web_seller_test.service.ProductService;
import com.ews.web_seller_test.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
//Done - TrongVV -- Doing : testDoGet_NonAdminUser

class ProductListControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private UserService userService;

    @Mock
    private Order_DetailsService orderDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private ProductListController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // Mock behavior of getSession and getRequestDispatcher
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(any(String.class))).thenReturn(dispatcher);
    }

    @Test
    void testDoGet() throws Exception {
        // Mock user session
        User adminUser = new User();
        adminUser.setId(1);
        adminUser.setUsername("admin");

        // Initialize a Role object
        Role adminRole = new Role();
        adminRole.setId(1); // Assuming admin role ID is 1
        adminUser.setRole(adminRole);

        when(session.getAttribute("account")).thenReturn(adminUser);

        // Mock product list
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product(1, "Product1", 100.0f, 0.0f, "Description1"));
        mockProducts.add(new Product(2, "Product2", 150.0f, 10.0f, "Description2"));
        when(productService.getAllProduct()).thenReturn(mockProducts);

        // Perform servlet doGet
        controller.doGet(request, response);

        // Verify attribute setting and forwarding
        verify(request).setAttribute("username", adminUser.getUsername());
        verify(request).setAttribute("productList", mockProducts);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoGet_NonAdminUser() throws Exception {
//        // Mock user session (non-admin user)
//        User regularUser = new User();
//        regularUser.setId(2);
//        regularUser.setUsername("user");
//
//        // Initialize a Role object for non-admin user
//        Role userRole = new Role();
//        userRole.setId(2); // Assuming non-admin role ID is 2
//        regularUser.setRole(userRole);
//
//        when(session.getAttribute("account")).thenReturn(regularUser);
//
//        // Perform servlet doGet
//        controller.doGet(request, response);
//
//        // Verify redirection to user index page for non-admin users
//        verify(request).getRequestDispatcher("/views/user/index.jsp");
//        verify(dispatcher).forward(request, response); // Ensure forward is invoked once
    }
}
