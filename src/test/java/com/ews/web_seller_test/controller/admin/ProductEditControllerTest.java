package com.ews.web_seller_test.controller.admin;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.Role;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.CategoryService;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
// Done - Trong VV  - Doing : testDoPost_Image
class ProductEditControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private ProductEditController controller;

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
        User adminUser = createMockUserWithRole(1); // Replace 1 with a valid role ID

        // Initialize a Category and Product for testing
        Category category = new Category();
        category.setId(1);
        category.setName("TestCategory");

        Product product = new Product();
        product.setId(1);
        product.setName("TestProduct");
        product.setCategory(category);

        // Mock ProductService to return predefined product
        when(productService.getProduct(1)).thenReturn(product);

        // Mock CategoryService to return a list of categories
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        when(categoryService.getAllCategory()).thenReturn(categories);

        // Mock session attribute retrieval
        when(session.getAttribute("account")).thenReturn(adminUser);

        // Mock request parameters
        when(request.getParameter("id")).thenReturn("1");

        // Perform servlet doGet
        controller.doGet(request, response);

        // Verify attribute setting and forward to edit-product.jsp
        verify(request).setAttribute("username", adminUser.getUsername());
        verify(request).setAttribute("id", "1");
        verify(request).setAttribute("product", product);
        verify(request).setAttribute("categories", categories);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPost_Image() throws Exception {
//        // Mock user session
//        User adminUser = createMockUserWithRole(1); // Replace 1 with a valid role ID
//
//        // Mock session attribute retrieval
//        when(session.getAttribute("account")).thenReturn(adminUser);
//
//        // Mock request parameters
//        when(request.getParameter("info")).thenReturn("image");
//        when(request.getParameter("id")).thenReturn("1");
//
//        // Mock ProductService to return predefined product
//        Product product = new Product();
//        product.setId(1);
//        when(productService.getProduct(1)).thenReturn(product);
//
//        // Mock Part and file upload
//        when(request.getPart("fileImage")).thenReturn(null); // mock Part instance
//
//        // Perform servlet doPost for image upload
//        controller.doPost(request, response);
//
//        // Verify ProductService method calls and response content
//        verify(productService).getProduct(1);
//        verify(productService).editProduct(product);
//        verify(response).getWriter().println("File upload failed due to an error: null");
    }

    private User createMockUserWithRole(int roleId) {
        Role role = new Role();
        role.setId(roleId); // Set a valid role ID

        User user = new User();
        user.setRole(role);

        return user;
    }
}
