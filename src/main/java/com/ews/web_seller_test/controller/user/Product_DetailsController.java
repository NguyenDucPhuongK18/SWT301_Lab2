package com.ews.web_seller_test.controller.user;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.CategoryService;
import com.ews.web_seller_test.service.ProductService;
import com.ews.web_seller_test.service.impl.CategoryServiceImpl;
import com.ews.web_seller_test.service.impl.ProductServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "Product_DetailsController", urlPatterns = {"/product/detail"})
public class Product_DetailsController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        Product product = productService.getProduct(Integer.parseInt(id));
        List<Category> categoryList = categoryService.getAllCategory();
        List<Product> productList = productService.getAllProduct();

        request.setAttribute("categories", categoryList);
        request.setAttribute("productList", productList);
        request.setAttribute("product", product);

        // Get session username
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("user", user);
        }

        int amount = 0;
        HttpSession httpSession = request.getSession();
        Map<Integer, Order_Details> mapList = null;
        if (httpSession.getAttribute("cart") != null) {
            mapList = (Map<Integer, Order_Details>) httpSession.getAttribute("cart");
            amount = mapList.size();
            request.setAttribute("amount", amount);
        }

        // Forward request to JSP page
        String jspPath = "/views/user/single-product.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            // Handle the case where dispatcher is null
            throw new ServletException("Unable to dispatch to JSP: " + jspPath);
        }
    }
}
