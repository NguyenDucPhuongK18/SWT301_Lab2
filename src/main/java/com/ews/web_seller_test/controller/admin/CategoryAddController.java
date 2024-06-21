package com.ews.web_seller_test.controller.admin;

import java.io.IOException;
import java.util.List;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.*;
import com.ews.web_seller_test.service.impl.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "CategoryAddController", urlPatterns = {"/admin/category/add"})
public class CategoryAddController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService cateService = new CategoryServiceImpl();
    UserService userService = new UserServiceImpl();
    Order_DetailsService orderDetailsService = new Order_DetailsImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object obj=session.getAttribute("account");
        User user=(User) obj;
        req.setAttribute("username", user.getUsername());

        if(user.getRole().getId() == 2) {
            req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
            return;
        }

        if(user.getRole().getId() == 1) {
            List<Product> productList = productService.getAllProduct();
            List<User> userList = userService.getAllUser();
            List<Category> categoryList = cateService.getAllCategory();
            List<Order_Details> orderDetailsList = orderDetailsService.getAllOrder_Details();
            req.getRequestDispatcher("/views/admin/view/add-category.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("/views/user/error404.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Object obj=session.getAttribute("account");
        User user=(User) obj;
        request.setAttribute("username", user.getUsername());

        String name = request.getParameter("cate_name");
        Category category = new Category();
        CategoryService cateService = new CategoryServiceImpl();
        category.setName(name);
        cateService.insertCategory(category);
        response.sendRedirect(request.getContextPath() + "/admin/category/list");
    }
}
