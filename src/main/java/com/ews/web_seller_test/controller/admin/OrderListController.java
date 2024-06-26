package com.ews.web_seller_test.controller.admin;

import java.io.IOException;
import java.util.List;

import com.ews.web_seller_test.model.Category;
import com.ews.web_seller_test.model.Order_Details;
import com.ews.web_seller_test.model.Product;
import com.ews.web_seller_test.model.User;
import com.ews.web_seller_test.service.*;
import com.ews.web_seller_test.service.impl.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "OrderListController", urlPatterns = {"/admin/order/list"})
public class OrderListController extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService cateService = new CategoryServiceImpl();
    UserService userService = new UserServiceImpl();
    Order_DetailsService orderDetailsService = new Order_DetailsImpl();
    OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login"); // Redirect if user not logged in
            return;
        }

        if (user.getRole().getId() == 2) {
            req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
            return; // Exit method after forwarding
        }

        req.setAttribute("username", user.getUsername());

        List<Product> productList = productService.getAllProduct();
        List<User> userList = userService.getAllUser();
        List<Category> categoryList = cateService.getAllCategory();
        List<Order_Details> orderDetailsList = orderDetailsService.getAllOrder_Details();

        req.setAttribute("orderDetailsList", orderDetailsList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/view/list-order.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
}
