package com.ews.web_seller_test.controller.admin;

import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.model.Role;
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

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ProductListControllerTest {

    @InjectMocks
    private ProductListController productListController;

    @Mock
    private ProductService productService;

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
        User mockUser = new User();
        Role role = new Role();
        role.setId(1); // Assuming 1 is an admin role
        mockUser.setRole(role);
        when(session.getAttribute("account")).thenReturn(mockUser);

        List<Product> mockProductList = new ArrayList<>();
        mockProductList.add(new Product()); // Add some mock data as needed
        when(productService.getAllProduct()).thenReturn(mockProductList);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/views/admin/view/list-product.jsp")).thenReturn(requestDispatcher);

        // Call the doGet method
        productListController.doGet(request, response);

        // Verify interactions
        verify(productService, times(1)).getAllProduct();
        verify(request, times(1)).setAttribute("productList", mockProductList);
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void testDoGetWithNonAdminUser() throws Exception {
        // Mock data
        User mockUser = new User();
        Role role = new Role();
        role.setId(2); // Assuming 2 is a non-admin role
        mockUser.setRole(role);
        when(session.getAttribute("account")).thenReturn(mockUser);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/views/user/index.jsp")).thenReturn(requestDispatcher);

        // Call the doGet method
        productListController.doGet(request, response);

        // Verify interactions
        verify(requestDispatcher, times(1)).forward(request, response);
        verify(request, never()).setAttribute(eq("productList"), any());
    }

    @Test
    void testDoPost() throws Exception {
        // Mock PrintWriter and simulate response
        PrintWriter mockPrintWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(mockPrintWriter);

        // Call the doPost method
        productListController.doPost(request, response);

        // Verify interactions
        verify(response, times(1)).setContentType("text/html;charset=UTF-8");
    }
}
