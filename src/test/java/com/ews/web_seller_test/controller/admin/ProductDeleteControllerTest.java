package com.ews.web_seller_test.controller.admin;

import com.ews.web_seller_test.model.Role;
import com.ews.web_seller_test.model.User;
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

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
//Done - TrongVV
class ProductDeleteControllerTest {

    @InjectMocks
    private ProductDeleteController servlet;

    @Mock
    private ProductService productService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn("1"); // Mocking product ID parameter
    }

    @Test
    void testDoGet_AdminUser() throws Exception {
        // Mock user session
        User adminUser = new User();
        adminUser.setId(1);
        adminUser.setUsername("admin");
        when(session.getAttribute("account")).thenReturn(adminUser);

        // Mock redirect behavior
        doNothing().when(response).sendRedirect(anyString());

        // Perform servlet doGet
        servlet.doGet(request, response);

        // Verify product deletion interaction
        verify(productService, times(1)).deleteProduct(1); // Verify deleteProduct method called once with ID 1
    }

    @Test
    void testDoGet_NonAdminUser() throws Exception {
        // Mock user session
        User nonAdminUser = new User();
        nonAdminUser.setId(2);
        nonAdminUser.setUsername("user");
        nonAdminUser.setRole(new Role(3, "User")); // Assuming non-admin user role is not ID 2

        when(session.getAttribute("account")).thenReturn(nonAdminUser);

        // Mock request parameters and attributes
        when(request.getParameter("id")).thenReturn("1");

        // Perform servlet doGet
        servlet.doGet(request, response);

        // Verify request attribute settings
        verify(request).setAttribute("username", "user");

        // Verify dispatcher forward to user index page
        verify(request, never()).getRequestDispatcher("/views/user/index.jsp");
        verify(dispatcher, never()).forward(request, response); // Ensure no forward happens
        verify(response).sendRedirect(request.getContextPath() + "/admin/product/list"); // Ensure redirect to admin list page
    }

}
