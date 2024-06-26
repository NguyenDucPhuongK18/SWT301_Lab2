package com.ews.web_seller_test.controller.user;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
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

class ProductSearchByNameTest {

    @InjectMocks
    private ProductSearchByName productSearchByName;

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
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDoGet() throws Exception {
        // Mock data
        List<Category> mockCategoryList = new ArrayList<>();
        mockCategoryList.add(new Category()); // Add some mock data as needed
        when(categoryService.getAllCategory()).thenReturn(mockCategoryList);

        User mockUser = new User();
        mockUser.setUsername("testUser");
        when(session.getAttribute("account")).thenReturn(mockUser);

        String txtSearch = "testProduct";
        int mockCount = 10;
        when(productService.countProduct(txtSearch)).thenReturn(mockCount);

        int pageSize = 8;
        int endPage = (mockCount % pageSize == 0) ? mockCount / pageSize : mockCount / pageSize + 1;
        int indexPage = 1; // Assuming default index page

        List<Product> mockProductList = new ArrayList<>();
        mockProductList.add(new Product()); // Add some mock data as needed
        when(productService.searchProductByName(txtSearch, indexPage, pageSize)).thenReturn(mockProductList);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getParameter("txtSearch")).thenReturn(txtSearch);
        when(request.getParameter("indexPage")).thenReturn(String.valueOf(indexPage));

        // Call the doGet method
        productSearchByName.doGet(request, response);

        // Verify interactions
        verify(categoryService, times(1)).getAllCategory();
        verify(session, times(1)).getAttribute("account");
        verify(productService, times(1)).countProduct(txtSearch);
        verify(productService, times(1)).searchProductByName(txtSearch, indexPage, pageSize);
        verify(request, times(1)).setAttribute(eq("categoryList"), anyList());
        verify(request, times(1)).setAttribute(eq("username"), eq("testUser"));
        verify(request, times(1)).setAttribute(eq("count"), eq(mockCount));
        verify(request, times(1)).setAttribute(eq("end"), eq(endPage));
        verify(request, times(1)).setAttribute(eq("indexPage"), eq(indexPage));
        verify(request, times(1)).setAttribute(eq("productList"), anyList());
        verify(request, times(1)).setAttribute(eq("txtSearch"), eq(txtSearch));
        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
