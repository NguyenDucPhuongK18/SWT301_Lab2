package com.ews.web_seller_test.controller.admin;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ews.web_seller_test.dao.CategoryDao;
import com.ews.web_seller_test.dao.DBContext;
import com.ews.web_seller_test.dao.impl.CategoryDaoImpl;
import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Role;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.Order_DetailsService;
import com.ews.web_seller_test.service.ProductService;
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

import java.sql.Connection;

class CategoryAddControllerTest {

    @InjectMocks
    private CategoryAddController controller;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @Mock
    private CategoryService cateService;

    @Mock
    private CategoryDaoImpl categoryDao;

    @Mock
    private Order_DetailsService orderDetailsService;

    @Mock
    private Connection mockConnection;

    @Mock
    private DBContext dbContext;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryDao.connection = mockConnection;
    }

    @Test
    void testDoGet_AdminRole() {
        assertDoesNotThrow(() -> {
            User user = new User();
            Role role = new Role();
            role.setId(1); // Admin role
            user.setRole(role);
            user.setUsername("admin");

            // Set up mocks
            when(request.getSession()).thenReturn(session);
            when(session.getAttribute("account")).thenReturn(user);
            when(request.getRequestDispatcher("/views/admin/view/add-category.jsp")).thenReturn(dispatcher);

            // Call the method
            controller.doGet(request, response);

            // Verify the interactions
            verify(request).setAttribute("username", "admin");
            verify(dispatcher).forward(request, response);
        });
    }

    @Test
    void testDoGet_UserRole() {
        assertDoesNotThrow(() -> {
            User user = new User();
            Role role = new Role();
            role.setId(2); // User role
            user.setRole(role);
            user.setUsername("user");

            // Set up mocks
            when(request.getSession()).thenReturn(session);
            when(session.getAttribute("account")).thenReturn(user);
            when(request.getRequestDispatcher("/views/user/index.jsp")).thenReturn(dispatcher);

            // Call the method
            controller.doGet(request, response);

            // Verify the interactions
            verify(request).setAttribute("username", "user");
            verify(dispatcher).forward(request, response);
        });
    }

    @Test
    void testDoGet_InvalidRole() {
        assertDoesNotThrow(() -> {
            User user = new User();
            Role role = new Role();
            role.setId(3); // Invalid role
            user.setRole(role);
            user.setUsername("invalid");

            // Set up mocks
            when(request.getSession()).thenReturn(session);
            when(session.getAttribute("account")).thenReturn(user);
            when(request.getRequestDispatcher("/views/user/error404.jsp")).thenReturn(dispatcher);

            // Call the method
            controller.doGet(request, response);

            // Verify the interactions
            verify(request).setAttribute("username", "invalid");
            verify(dispatcher).forward(request, response);
        });
    }

    @Test
    void testDoPost() {
        assertDoesNotThrow(() -> {
            // Mock user object with admin role
            User user = new User();
            Role role = new Role();
            role.setId(1);
            user.setRole(role);
            user.setUsername("admin");

            Category category = new Category();
            category.setName("Electronics");

            // Set up mocks
            when(request.getSession()).thenReturn(session);
            when(session.getAttribute("account")).thenReturn(user);
            when(request.getParameter("cate_name")).thenReturn(category.getName());
            when(cateService.insertCategory(category)).thenReturn(true);

            // Call the method
            controller.doPost(request, response);

            Category previousAddedOne = cateService.getCategory(1);
            assertNotNull(previousAddedOne);
            assertEquals("Electronics", previousAddedOne.getName());

            // Verify the interactions
            verify(cateService).insertCategory(any(Category.class));
            verify(response).sendRedirect(request.getContextPath() + "/admin/category/list");
        });
    }
}
