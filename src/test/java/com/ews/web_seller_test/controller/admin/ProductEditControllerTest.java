package com.ews.web_seller_test.controller.admin;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.model.Role;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ProductEditControllerTest {

    @InjectMocks
    private ProductEditController productEditController;

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

    @Mock
    private ServletContext servletContext;

    @Mock
    private Part filePart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() throws Exception {
        when(servletContext.getRealPath("/assets/user/img/product")).thenReturn("/path/to/upload");

        productEditController.init();

        verify(servletContext, times(1)).getRealPath("/assets/user/img/product");
        Path path = Paths.get("/path/to/upload");
        File uploadDir = path.toFile();
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    @Test
    void testDoGet() throws Exception {
        User mockUser = new User();
        Role role = new Role();
        role.setId(1); // Assuming 1 is an admin role
        mockUser.setRole(role);
        when(session.getAttribute("account")).thenReturn(mockUser);

        String mockId = "1";
        when(request.getParameter("id")).thenReturn(mockId);
        when(productService.getProduct(Integer.parseInt(mockId))).thenReturn(new Product());
        when(categoryService.getAllCategory()).thenReturn(new ArrayList<>());

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/views/admin/view/edit-product.jsp")).thenReturn(requestDispatcher);

        productEditController.doGet(request, response);

        verify(productService, times(1)).getProduct(Integer.parseInt(mockId));
        verify(categoryService, times(1)).getAllCategory();
        verify(request, times(1)).setAttribute(eq("product"), any(Product.class));
        verify(request, times(1)).setAttribute(eq("categories"), anyList());
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void testDoPostUpdateInfo() throws Exception {
        User mockUser = new User();
        Role role = new Role();
        role.setId(1); // Assuming 1 is an admin role
        mockUser.setRole(role);
        when(session.getAttribute("account")).thenReturn(mockUser);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("info")).thenReturn("info");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("Test Product");
        when(request.getParameter("price")).thenReturn("100");
        when(request.getParameter("discount")).thenReturn("10");
        when(request.getParameter("des")).thenReturn("Test Description");
        when(request.getParameter("category")).thenReturn("1");
        when(categoryService.getCategory(1)).thenReturn(new Category());

        productEditController.doPost(request, response);

        verify(productService, times(1)).editProduct(any(Product.class));
        verify(response, times(1)).sendRedirect(request.getContextPath() + "/admin/product/list");
    }

    @Test
    void testDoPostUpdateImage() throws Exception {
        User mockUser = new User();
        Role role = new Role();
        role.setId(1); // Assuming 1 is an admin role
        mockUser.setRole(role);
        when(session.getAttribute("account")).thenReturn(mockUser);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("info")).thenReturn("image");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getPart("fileImage")).thenReturn(filePart);
        when(filePart.getSubmittedFileName()).thenReturn("testImage.jpg");
        InputStream mockInputStream = mock(InputStream.class);
        when(filePart.getInputStream()).thenReturn(mockInputStream);
        Files.copy(mockInputStream, Paths.get("/path/to/upload" + File.separator + "testImage.jpg"));
        when(productService.getProduct(1)).thenReturn(new Product());

        productEditController.doPost(request, response);

        verify(filePart, times(1)).getSubmittedFileName();
        verify(filePart, times(1)).getInputStream();
        verify(productService, times(1)).editProduct(any(Product.class));
        verify(response, times(1)).sendRedirect(request.getContextPath() + "/admin/product/list");
    }
}
