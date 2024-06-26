package com.ews.web_seller_test.controller.user;

import com.ews.web_seller_test.model.Order;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.OrderService;
import com.ews.web_seller_test.service.Order_DetailsService;
import com.ews.web_seller_test.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private OrderService orderService;

    @Mock
    private Order_DetailsService orderDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private OrderController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGet() throws Exception {
        // Mock HttpSession and User
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        // Mock session attribute
        when(session.getAttribute("account")).thenReturn(user);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/views/user/checkout.jsp")).thenReturn(requestDispatcher);

        // Mock cart session attribute
        Map<Integer, Order_Details> cart = new HashMap<>();
        cart.put(1, new Order_Details());
        when(session.getAttribute("cart")).thenReturn(cart);

        // Call the controller method
        controller.doGet(request, response);

        // Verify attributes set on request
        verify(request, times(1)).setAttribute("amount", cart.size());
        verify(request, times(1)).setAttribute("username", user.getUsername());
        verify(request, times(1)).setAttribute("user", user);

        // Verify forwarding to the checkout.jsp view
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void testDoPost() throws Exception {
        // Mock HttpSession and User
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        // Mock session attribute
        when(session.getAttribute("account")).thenReturn(user);
        when(request.getSession()).thenReturn(session);

        // Mock request parameters
        when(request.getParameter("total_price_origin")).thenReturn("100.0");
        when(request.getParameter("total_price")).thenReturn("90.0");
        when(request.getParameter("total_discount")).thenReturn("10.0");
        when(request.getParameter("phone")).thenReturn("1234567890");
        when(request.getParameter("address")).thenReturn("Test Address");
        when(request.getParameter("note")).thenReturn("Test Note");
        when(request.getParameter("total_quantity")).thenReturn("1");

        // Mock cart session attribute
        Map<Integer, Order_Details> cart = new HashMap<>();
        cart.put(1, new Order_Details());
        when(session.getAttribute("cart")).thenReturn(cart);

        // Mock OrderService insertOrder method
        when(orderService.insertOrder(any(Order.class))).thenReturn(1);

        // Mock RequestDispatcher and its behavior
        when(request.getRequestDispatcher("/views/user/order_success.jsp")).thenReturn(requestDispatcher);

        // Call the controller method
        controller.doPost(request, response);

        // Verify attributes set on request
        verify(request, times(1)).setAttribute("amount", cart.size());
        verify(request, times(1)).setAttribute("username", user.getUsername());
        verify(request, times(1)).setAttribute("user", user);

        // Verify order insertion
        verify(orderService, times(1)).insertOrder(any(Order.class));

        // Verify forwarding to order_success.jsp view
        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
