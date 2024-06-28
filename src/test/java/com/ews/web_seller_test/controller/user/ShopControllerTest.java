package com.ews.web_seller_test.controller.user;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.CategoryService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class ShopControllerTest {

    @InjectMocks
    private ShopController shopController;

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

        Map<Integer, Order_Details> mockCart = new HashMap<>();
        mockCart.put(1, new Order_Details()); // Add some mock data as needed
        when(session.getAttribute("cart")).thenReturn(mockCart);

        List<Product> mockProductList = new ArrayList<>();
        mockProductList.add(new Product(1, "Product1")); // Add some mock data as needed
        when(productService.countProduct(anyString())).thenReturn(10);
        when(productService.searchProductByName(anyString(), anyInt(), anyInt())).thenReturn(mockProductList);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        // Call the doGet method
        shopController.doGet(request, response);

        // Verify interactions
        verify(categoryService, times(1)).getAllCategory();
        verify(session, times(1)).getAttribute("account");
        verify(session, times(1)).getAttribute("cart");
        verify(productService, times(1)).countProduct(anyString());
        verify(productService, times(1)).searchProductByName(anyString(), anyInt(), anyInt());
        verify(request, times(1)).setAttribute(eq("categoryList"), anyList());
        verify(request, times(1)).setAttribute(eq("username"), eq("testUser"));
        verify(request, times(1)).setAttribute(eq("amount"), anyInt());
        verify(request, times(1)).setAttribute(eq("count"), anyInt());
        verify(request, times(1)).setAttribute(eq("productList"), anyList());
        verify(request, times(1)).setAttribute(eq("indexPage"), anyInt());
        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
