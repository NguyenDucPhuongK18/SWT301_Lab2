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

@WebServlet(name = "CategoryEditController", urlPatterns = {"/admin/category/edit"})
public class CategoryEditController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService cateService = new CategoryServiceImpl();
    UserService userService = new UserServiceImpl();
    Order_DetailsService orderDetailsService = new Order_DetailsImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object obj=session.getAttribute("account");
        User user=(User) obj;
        if(user.getRole().getId() == 2) {
            req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
        }
        req.setAttribute("username", user.getUsername());

        List<Product> productList = productService.getAllProduct();
        List<User> userList = userService.getAllUser();
        List<Category> categoryList = cateService.getAllCategory();
        List<Order_Details> orderDetailsList = orderDetailsService.getAllOrder_Details();

        String id = req.getParameter("id");
        Category category = cateService.getCategory(Integer.parseInt(id));
        req.setAttribute("category", category);
        req.getRequestDispatcher("/views/admin/view/edit-category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Category category = new Category();
        category.setId(Integer.parseInt(request.getParameter("id")));
        category.setName(request.getParameter("cate_name"));
        cateService.editCategory(category);
        response.sendRedirect(request.getContextPath()+"/admin/category/list");
    }
}
