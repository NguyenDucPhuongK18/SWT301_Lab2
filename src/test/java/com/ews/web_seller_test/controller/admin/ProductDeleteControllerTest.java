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

import static org.mockito.Mockito.*;

class ProductDeleteControllerTest {

    @InjectMocks
    private ProductDeleteController productDeleteController;

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
    void testDoGetAdminUser() throws Exception {
        User mockUser = new User();
        Role role = new Role();
        role.setId(1); // Assuming 1 is an admin role
        mockUser.setRole(role);
        when(session.getAttribute("account")).thenReturn(mockUser);

        String mockId = "1";
        when(request.getParameter("id")).thenReturn(mockId);
        when(request.getSession()).thenReturn(session);

        productDeleteController.doGet(request, response);

        verify(productService, times(1)).deleteProduct(Integer.parseInt(mockId));
        verify(response, times(1)).sendRedirect(request.getContextPath() + "/admin/product/list");
    }

    @Test
    void testDoGetRegularUser() throws Exception {
        User mockUser = new User();
        Role role = new Role();
        role.setId(2); // Assuming 2 is a regular user role
        mockUser.setRole(role);
        when(session.getAttribute("account")).thenReturn(mockUser);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/views/user/index.jsp")).thenReturn(requestDispatcher);

        productDeleteController.doGet(request, response);

        verify(requestDispatcher, times(1)).forward(request, response);
        verify(productService, never()).deleteProduct(anyInt());
    }

    @Test
    void testDoPost() throws Exception {
        productDeleteController.doPost(request, response);
        // Since doPost is not implemented, just check that response content type is set correctly
        verify(response, times(1)).setContentType("text/html;charset=UTF-8");
    }
}
