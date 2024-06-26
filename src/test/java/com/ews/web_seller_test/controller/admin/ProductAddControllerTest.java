package com.ews.web_seller_test.controller.admin;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.Role;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.ProductService;
import com.ews.web_seller_test.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
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

import static org.mockito.Mockito.*;

class ProductAddControllerTest {

    @InjectMocks
    private ProductAddController productAddController;

    @Mock
    private ProductService productService;

    @Mock
    private CategoryService cateService;

    @Mock
    private UserService userService;

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
    void testInit() throws ServletException {
        when(servletContext.getRealPath("/assets/user/img/product")).thenReturn("test/upload/path");
        File mockFile = mock(File.class);
        when(mockFile.exists()).thenReturn(false);

        productAddController.init();

        verify(mockFile, times(1)).mkdirs();
    }

    @Test
    void testDoGetAdminUser() throws Exception {
        User mockUser = new User();
        Role role = new Role();
        role.setId(1); // Assuming 1 is an admin role
        mockUser.setRole(role);
        when(session.getAttribute("account")).thenReturn(mockUser);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/views/admin/view/add-product.jsp")).thenReturn(requestDispatcher);

        productAddController.doGet(request, response);

        verify(requestDispatcher, times(1)).forward(request, response);
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

        productAddController.doGet(request, response);

        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void testDoPostInfo() throws Exception {
        when(request.getParameter("info")).thenReturn("info");
        when(request.getParameter("name")).thenReturn("Test Product");
        when(request.getParameter("price")).thenReturn("100.0");
        when(request.getParameter("discount")).thenReturn("10.0");
        when(request.getParameter("des")).thenReturn("Test Description");
        when(request.getParameter("category")).thenReturn("1");

        Category mockCategory = new Category();
        when(cateService.getCategory(1)).thenReturn(mockCategory);

        when(productService.getIdInsertProduct(any())).thenReturn(1);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/views/admin/view/add-product.jsp")).thenReturn(requestDispatcher);

        productAddController.doPost(request, response);

        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void testDoPostImage() throws Exception {
        when(request.getParameter("info")).thenReturn("image");
        when(request.getPart("fileImage")).thenReturn(filePart);
        when(filePart.getSubmittedFileName()).thenReturn("test.jpg");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("pidEdit")).thenReturn(1);

        Path path = mock(Path.class);
        when(path.toString()).thenReturn("test/upload/path/test.jpg");

        InputStream inputStream = mock(InputStream.class);
        when(filePart.getInputStream()).thenReturn(inputStream);

        Product mockProduct = new Product();
        when(productService.getProduct(1)).thenReturn(mockProduct);

        productAddController.doPost(request, response);

        verify(productService, times(1)).editProduct(mockProduct);
        verify(response, times(1)).sendRedirect(request.getContextPath() + "/admin/product/list");
    }

    @Test
    void testDoPostNoInfo() throws Exception {
        when(request.getParameter("info")).thenReturn("");

        productAddController.doPost(request, response);

        verify(response, times(1)).setContentType("text/html;charset=UTF-8");
    }
}
