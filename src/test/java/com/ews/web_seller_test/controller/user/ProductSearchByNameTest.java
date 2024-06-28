package com.ews.web_seller_test.controller.user;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
//Done - TrongVV

class ProductSearchByNameTest {

    @InjectMocks
    private ProductSearchByName servlet;

    @Mock
    private ProductService productService;

    @Mock
    private CategoryService categoryService;

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
    }

    @Test
    void testDoGet() throws ServletException, IOException {
        // Mock data
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(new Category(1, "TestCategory"));

        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product(1, "Test Product", "Description", 10.0f, new Category(1, "TestCategory")));

        String txtSearch = "Test";
        int indexPage = 1;
        int pageSize = 8;
        int count = 1;
        int endPage = 1;

        // Mock service responses
        when(categoryService.getAllCategory()).thenReturn(mockCategories);
        when(productService.countProduct(txtSearch)).thenReturn(count);
        when(productService.searchProductByName(txtSearch, indexPage, pageSize)).thenReturn(mockProducts);

        // Mock request parameters
        when(request.getParameter("txtSearch")).thenReturn(txtSearch);
        when(request.getParameter("indexPage")).thenReturn(String.valueOf(indexPage));

        // Mock session attributes
        User mockUser = new User(1, "testUser", "password");
        when(session.getAttribute("account")).thenReturn(mockUser);

        // Mock forwarding behavior
        when(request.getRequestDispatcher("/views/user/shopping.jsp")).thenReturn(dispatcher);

        // Invoke servlet doGet method
        servlet.doGet(request, response);

        // Verify request attribute settings
        verify(request).setAttribute(eq("categoryList"), eq(mockCategories));
        verify(request).setAttribute(eq("productList"), eq(mockProducts));
        verify(request).setAttribute(eq("txtSearch"), eq(txtSearch));
        verify(request).setAttribute(eq("count"), eq(count));
        verify(request).setAttribute(eq("end"), eq(endPage));
        verify(request).setAttribute(eq("indexPage"), eq(indexPage));
        verify(request).setAttribute(eq("start"), eq(1));
        verify(request).setAttribute(eq("en"), eq(8));
        verify(request).setAttribute(eq("username"), eq(mockUser.getUsername()));

        // Verify forwarding to JSP page
        verify(dispatcher).forward(request, response);
    }

    // No test needed for doPost since it has no functionality

}
