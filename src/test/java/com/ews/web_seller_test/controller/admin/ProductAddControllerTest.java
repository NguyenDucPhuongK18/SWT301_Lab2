package com.ews.web_seller_test.controller.admin;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.Role;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.Order_DetailsService;
import com.ews.web_seller_test.service.ProductService;
import com.ews.web_seller_test.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
//Done - TrongVV --- Doing : testDoPost_Image

class ProductAddControllerTest {

    @InjectMocks
    private ProductAddController servlet;

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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher); // Mock getRequestDispatcher to return a non-null RequestDispatcher
    }

    @Test
    void testDoGet_AdminUser() throws Exception {
        // Mock user session
        User adminUser = new User();
        adminUser.setId(1);
        adminUser.setUsername("admin");
        adminUser.setRole(new Role(1, "Admin"));
        when(session.getAttribute("account")).thenReturn(adminUser);

        // Mock service responses
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(new Category(1, "TestCategory"));
        when(categoryService.getAllCategory()).thenReturn(mockCategories);
        when(productService.getAllProduct()).thenReturn(new ArrayList<>());
        when(userService.getAllUser()).thenReturn(new ArrayList<>());
        when(orderDetailsService.getAllOrder_Details()).thenReturn(new ArrayList<>());

        // Perform servlet doGet
        servlet.doGet(request, response);

        // Verify request attribute settings
        verify(request).setAttribute("username", "admin");
        verify(request).setAttribute("categories", mockCategories);

        // Verify dispatcher forward
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPost_Info() throws Exception {
        // Mock user session
        User adminUser = new User();
        adminUser.setId(1);
        adminUser.setUsername("admin");
        adminUser.setRole(new Role(1, "Admin"));
        when(session.getAttribute("account")).thenReturn(adminUser);

        // Mock request parameters and attributes
        when(request.getParameter("info")).thenReturn("info");
        when(request.getParameter("name")).thenReturn("Test Product");
        when(request.getParameter("price")).thenReturn("100.0");
        when(request.getParameter("discount")).thenReturn("10.0");
        when(request.getParameter("des")).thenReturn("Description");
        when(request.getParameter("category")).thenReturn("1");

        // Mock service responses
        Category category = new Category(1, "TestCategory");
        when(categoryService.getCategory(1)).thenReturn(category);

        // Mock product service behavior
        Product insertedProduct = new Product();
        insertedProduct.setId(1);
        when(productService.getIdInsertProduct(any(Product.class))).thenReturn(1);

        // Mock session attribute behavior
        when(session.getAttribute("pidEdit")).thenReturn(1);

        // Mock request dispatch
        when(request.getRequestDispatcher("/views/admin/view/add-product.jsp")).thenReturn(dispatcher);

        // Perform servlet doPost for "info" scenario
        servlet.doPost(request, response);

        // Verify session attribute setting
        verify(session).setAttribute("pidEdit", 1);

        // Verify dispatcher forward
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPost_Image() throws Exception {
//        // Mock user session
//        User adminUser = new User();
//        adminUser.setId(1);
//        adminUser.setUsername("admin");
//        adminUser.setRole(new Role(1, "Admin"));
//        when(session.getAttribute("account")).thenReturn(adminUser);
//
//        // Mock request parameters and attributes
//        when(request.getParameter("info")).thenReturn("image");
//
//        // Mock Part for file upload
//        Part filePart = mock(Part.class);
//        when(request.getPart("fileImage")).thenReturn(filePart);
//        when(filePart.getSubmittedFileName()).thenReturn("test_image.png");
//        InputStream inputStream = new ByteArrayInputStream("test data".getBytes());
//        when(filePart.getInputStream()).thenReturn(inputStream);
//
//        // Mock session attribute behavior
//        when(session.getAttribute("pidEdit")).thenReturn(1);
//
//        // Mock product service behavior
//        Product product = new Product();
//        product.setId(1);
//        when(productService.getProduct(1)).thenReturn(product);
//
//        // Perform servlet doPost for "image" scenario
//        servlet.doPost(request, response);
//
//        // Verify product image setting
//        verify(productService).editProduct(product);
    }


}
