package com.ews.web_seller_test.controller.admin;

import com.ews.web_seller_test.model.*;
import com.ews.web_seller_test.service.*;
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

import static org.mockito.Mockito.*;

class OrdersEditControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private CategoryService cateService;

    @Mock
    private UserService userService;

    @Mock
    private Order_DetailsService orderDetailsService;

    @Mock
    private OrderService orderService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private OrdersEditController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGet() throws Exception {
        // Mock HttpSession and User
        User adminUser = new User();
        adminUser.setId(1);
        adminUser.setUsername("admin");

        // Initialize a Role object and set its ID
        Role adminRole = new Role();
        adminRole.setId(1); // Assuming admin role ID is 1
        adminUser.setRole(adminRole);

        when(session.getAttribute("account")).thenReturn(adminUser);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/views/admin/view/edit-order.jsp")).thenReturn(requestDispatcher);

        List<Product> productList = new ArrayList<>();
        when(productService.getAllProduct()).thenReturn(productList);

        List<User> userList = new ArrayList<>();
        when(userService.getAllUser()).thenReturn(userList);

        List<Category> categoryList = new ArrayList<>();
        when(cateService.getAllCategory()).thenReturn(categoryList);

        List<Order_Details> orderDetailsList = new ArrayList<>();
        when(orderDetailsService.getAllOrder_Details()).thenReturn(orderDetailsList);

        // Mock request parameter
        when(request.getParameter("id")).thenReturn("1");

        // Call the controller method
        controller.doGet(request, response);

        // Verify attributes set on request
        verify(request, times(1)).setAttribute("username", adminUser.getUsername());
        verify(request, times(1)).setAttribute("orderDetailsList", orderDetailsList);
        verify(request, times(1)).setAttribute("userList", userList);
        verify(request, times(1)).setAttribute("productList", productList);
        verify(request, times(1)).setAttribute("orderD_id", "1");

        // Verify forwarding to the edit-order.jsp view
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void testDoPost_AdminUser() throws Exception {
        // Mock HttpSession and User for admin scenario
        User adminUser = new User();
        adminUser.setId(1);
        adminUser.setUsername("admin");

        // Initialize a Role object and set its ID
        Role adminRole = new Role();
        adminRole.setId(1); // Assuming admin role ID is 1
        adminUser.setRole(adminRole);

        when(session.getAttribute("account")).thenReturn(adminUser);
        when(request.getSession()).thenReturn(session);

        // Mock request parameters
        when(request.getParameter("product")).thenReturn("1");
        when(request.getParameter("quantity")).thenReturn("5");
        when(request.getParameter("orderD_id")).thenReturn("1");

        // Mock getOrder_Details and getProduct methods
        Order_Details mockOrderDetails = new Order_Details();
        when(orderDetailsService.getOrder_Details(1)).thenReturn(mockOrderDetails);
        when(productService.getProduct(1)).thenReturn(new Product(1, "Product1")); // Mock a Product object

        // Call the controller method
        controller.doPost(request, response);

        // Verify behavior: editOrder_Details should be called once
        verify(orderDetailsService, times(1)).editOrder_Details(any(Order_Details.class));

        // Verify redirection to /admin/order/list
        verify(response, times(1)).sendRedirect(request.getContextPath() + "/admin/order/list");
    }
}
