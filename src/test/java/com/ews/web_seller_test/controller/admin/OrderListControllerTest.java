package com.ews.web_seller_test.controller.admin;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.Role;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.Order_DetailsService;
import com.ews.web_seller_test.service.OrderService;
import com.ews.web_seller_test.service.ProductService;
import com.ews.web_seller_test.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class OrderListControllerTest {

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
    private OrderListController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGet_AdminUser() throws Exception {
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
        when(request.getRequestDispatcher("/views/admin/view/list-order.jsp")).thenReturn(requestDispatcher);

        List<Product> productList = new ArrayList<>();
        when(productService.getAllProduct()).thenReturn(productList);

        List<User> userList = new ArrayList<>();
        when(userService.getAllUser()).thenReturn(userList);

        List<Category> categoryList = new ArrayList<>();
        when(cateService.getAllCategory()).thenReturn(categoryList);

        List<Order_Details> orderDetailsList = new ArrayList<>();
        when(orderDetailsService.getAllOrder_Details()).thenReturn(orderDetailsList);

        // Call the controller method
        controller.doGet(request, response);

        // Verify attributes set on request
        verify(request, times(1)).setAttribute("username", adminUser.getUsername());
        verify(request, times(1)).setAttribute("orderDetailsList", orderDetailsList);

        // Verify forwarding to the list-order.jsp view
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void testDoGet_NonAdminUser() throws Exception {
        // Mock HttpSession and User for non-admin scenario
        User nonAdminUser = new User();
        nonAdminUser.setId(2);
        nonAdminUser.setUsername("user");

        // Initialize a Role object and set its ID
        Role userRole = new Role();
        userRole.setId(2); // Assuming user role ID is 2
        nonAdminUser.setRole(userRole);

        when(session.getAttribute("account")).thenReturn(nonAdminUser);
        when(request.getSession()).thenReturn(session);

        // Mock the getRequestDispatcher call to return the mock requestDispatcher
        when(request.getRequestDispatcher("/views/user/index.jsp")).thenReturn(requestDispatcher);

        List<Product> productList = new ArrayList<>(); // Mock empty product list for non-admin scenario
        when(productService.getAllProduct()).thenReturn(productList);

        List<User> userList = new ArrayList<>(); // Mock empty user list for non-admin scenario
        when(userService.getAllUser()).thenReturn(userList);

        List<Category> categoryList = new ArrayList<>(); // Mock empty category list for non-admin scenario
        when(cateService.getAllCategory()).thenReturn(categoryList);

        List<Order_Details> orderDetailsList = new ArrayList<>(); // Mock empty order details list for non-admin scenario
        when(orderDetailsService.getAllOrder_Details()).thenReturn(orderDetailsList);

        // Call the controller method
        controller.doGet(request, response);

        // Verify forwarding to the user index.jsp view for non-admin users
        verify(requestDispatcher, times(1)).forward(request, response);
    }

}
